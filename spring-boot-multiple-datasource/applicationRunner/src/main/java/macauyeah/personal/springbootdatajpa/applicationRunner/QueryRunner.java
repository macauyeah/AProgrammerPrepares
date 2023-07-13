package macauyeah.personal.springbootdatajpa.applicationRunner;

import static java.lang.Thread.sleep;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import macauyeah.personal.springbootdatajpa.applicationRunner.service.QueryService;

@SpringBootApplication
// @formatter:off
@ComponentScan(basePackages = {
	"macauyeah.personal.springbootdatajpa.applicationRunner.configuration",
	"macauyeah.personal.springbootdatajpa.applicationRunner.service"
})
// @formatter:on
public class QueryRunner implements ApplicationRunner {
	private static Logger LOG = LoggerFactory.getLogger(QueryRunner.class);
	@Autowired
	private QueryService converterService;

	public static void main(String[] args) {
		SpringApplication.run(QueryRunner.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		if (acquireLock() == false) {
			return;
		}
		List<String> optionValues = args.getOptionValues("op");
		if (optionValues != null) {
			for (String optionValue : optionValues) {
				LOG.debug("getaa op:" + optionValue);
			}
		}
		converterService.selectOneCommitTwo();
	}

	private boolean acquireLock() {
		File file = new File(System.getProperty("java.io.tmpdir"), QueryRunner.class.getCanonicalName());
		try {
			FileChannel fc = FileChannel.open(file.toPath(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
			FileLock lock = fc.tryLock();
			if (lock == null) {
				LOG.info("another instance is running");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("unable acquire log");
			return false;
		}
		try {
			sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}
}
