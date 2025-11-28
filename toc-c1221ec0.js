// Populate the sidebar
//
// This is a script, and not included directly in the page, to control the total size of the book.
// The TOC contains an entry for each page, so if each page includes a copy of the TOC,
// the total size of the page becomes O(n**2).
class MDBookSidebarScrollbox extends HTMLElement {
    constructor() {
        super();
    }
    connectedCallback() {
        this.innerHTML = '<ol class="chapter"><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="index.html">前言 編寫這堆電子垃圾的原因 Why I write these articles - </a></span></li><li class="chapter-item expanded "><li class="part-title">Docker</li></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/DockerConcept101CN.html"><strong aria-hidden="true">1.</strong> Docker的基本概念</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/BinaryAsDockerImageCN.html"><strong aria-hidden="true">2.</strong> Build Command Line Application Docker Image - 中文</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/DeployDockerClusterCN.html"><strong aria-hidden="true">3.</strong> Deploy Docker Cluster - 中文</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/SwarmModeCommandCN.html"><strong aria-hidden="true">4.</strong> Swarm mode 上線</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/SwarmModeUndeployLeaveCN.html"><strong aria-hidden="true">5.</strong> Swarm mode 上線 2</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/SwarmModeRollbackCN.html"><strong aria-hidden="true">6.</strong> Swarm mode 上線 3 - Rollback 回滾</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/SwarmModeIPCN.html"><strong aria-hidden="true">7.</strong> Swarm mode 上線 4 - IP 設定</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/SwarmModeUpgrade.html"><strong aria-hidden="true">8.</strong> Swarm mode 上線 5 - Host OS 升級流程</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/SwarmModeLoadBalancer.html"><strong aria-hidden="true">9.</strong> Swarm mode 上線 6 - load balancer | 負載平衡器</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/SwarmModeTraefik.html"><strong aria-hidden="true">10.</strong> Swarm mode 上線 7 - load balancer | 反向代理</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/CronJobWithDockerCN.html"><strong aria-hidden="true">11.</strong> Schedule Job with Docker - 中文</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/DockerArgEnvCN.html"><strong aria-hidden="true">12.</strong> Docker Variable control - 中文</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/DistributionRegistry.html"><strong aria-hidden="true">13.</strong> Distribution Registry 的設置與維護</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/OracleCN.html"><strong aria-hidden="true">14.</strong> 於 Docker 中運行 Oracle</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/ImageScan.html"><strong aria-hidden="true">15.</strong> Docker 來源掃瞄</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/DockerNonRootUser.html"><strong aria-hidden="true">16.</strong> Docker 中的非管理員用户</a></span></li><li class="chapter-item expanded "><li class="part-title">Podman</li></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/SteamDeckWithPodmanCN.html"><strong aria-hidden="true">17.</strong> SteamDeck 上的 Podman</a></span></li><li class="chapter-item expanded "><li class="part-title">VM</li></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/MultipassPackerCN.html"><strong aria-hidden="true">18.</strong> Create custom Ubuntu cloud-img with Packer - 中文</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/MultipassStaticIpCN.html"><strong aria-hidden="true">19.</strong> Multipass with static IP - 中文</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/MultipassDockerClusterCN.html"><strong aria-hidden="true">20.</strong> Docker Swarm in Multipass - 中文</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/VmwareDockerClusterCN.html"><strong aria-hidden="true">21.</strong> Docker Swarm in Vmware - 中文</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/DockerWithNfsCN.html"><strong aria-hidden="true">22.</strong> Docker with NFS file store - 中文</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="VMDockerNotes/DockerSyslogCN.html"><strong aria-hidden="true">23.</strong> Docker with Syslog - 中文</a></span></li><li class="chapter-item expanded "><li class="spacer"></li></li><li class="chapter-item expanded "><li class="part-title">Git</li></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="gitNotes/gitcoworkflow.html"><strong aria-hidden="true">24.</strong> Git Co-Work Flow</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="gitNotes/github-flow.html"><strong aria-hidden="true">25.</strong> Github workflow</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="gitNotes/submodule.html"><strong aria-hidden="true">26.</strong> Git Submodule</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="gitNotes/git-merge-timing.html"><strong aria-hidden="true">27.</strong> Git Merge Timing</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="gitNotes/git-continuous-integration-strategy.html"><strong aria-hidden="true">28.</strong> Continuous Integration Strategy</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="gitNotes/git-mono-repo.html"><strong aria-hidden="true">29.</strong> Git Mono Repository</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="gitNotes/git-worktree.html"><strong aria-hidden="true">30.</strong> Git Worktree</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="gitNotes/git-revise-history.html"><strong aria-hidden="true">31.</strong> Git 修改歷史記錄</a></span></li><li class="chapter-item expanded "><li class="spacer"></li></li><li class="chapter-item expanded "><li class="part-title">Spring boot</li></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/springBootMavenCheatSheet.html"><strong aria-hidden="true">32.</strong> Spring Boot - Maven Cheat Sheet 貓紙</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/springBootWebSocket.html"><strong aria-hidden="true">33.</strong> Websocke and HTTP 1.1</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/springBootDataCodeFirst.html"><strong aria-hidden="true">34.</strong> Spring Data Jpa 自動化的選擇 - Code First</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/springBootMultipleDatasource.html"><strong aria-hidden="true">35.</strong> Multiple datasource</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/javaLambda.html"><strong aria-hidden="true">36.</strong> Java Lamdbda</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/javaLambda2.html"><strong aria-hidden="true">37.</strong> Java Lamdbda - Sorting</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/WebClientSSL.html"><strong aria-hidden="true">38.</strong> 升級 Reactor Netty 1.2.6：重新配置 SSL 設定</a></span></li><li class="chapter-item expanded "><li class="spacer"></li></li><li class="chapter-item expanded "><li class="part-title">Spring boot tutorial</li></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/springBootLesson/01-command-line-application.html"><strong aria-hidden="true">39.</strong> Spring Boot 01 - 萬物始於Spring boot context</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/springBootLesson/02-spring-data-jpa.html"><strong aria-hidden="true">40.</strong> Spring Boot 02 - 快速接入Database的選擇: Spring Data JPA</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/springBootLesson/03-spring-data-test-case.html"><strong aria-hidden="true">41.</strong> Spring Boot 03 - 做好Database的模組化及測試用例</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/springBootLesson/04-spring-web-api.html"><strong aria-hidden="true">42.</strong> Spring Boot 04 - 進入http json api 世代</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/springBootLesson/05-spring-web-spring-data.html"><strong aria-hidden="true">43.</strong> Spring Boot 05 - 為 http json api 加入登入要求</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/springBootLesson/06-spring-web-debug.html"><strong aria-hidden="true">44.</strong> Spring Boot 06 - Spring Boot Web 調試工具</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/springBootLesson/07-spring-web-api-validate.html"><strong aria-hidden="true">45.</strong> Spring Boot 07 - Spring Boot Web 加入限制</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/springBootLesson/spring-boot-web-exception-handler.html"><strong aria-hidden="true">46.</strong> Spring boot web api 異常處理</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/springBootLesson/08-spring-boot-properties-profile.html"><strong aria-hidden="true">47.</strong> Spring Boot 08 - 多情境設置 maven profile 與 application.properties</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/springBootLesson/09-spring-boot-properties-profile-container.html"><strong aria-hidden="true">48.</strong> Spring Boot 09 - 多情境設置 maven profile 與 application.properties </a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/springBootLesson/10-openapi-generator-spring-boot-java-client.html"><strong aria-hidden="true">49.</strong> Spring Boot 10 - OpenAPI自動生成 API 客戶端的步驟</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/springBootLesson/11-spring-data-schema-options.html"><strong aria-hidden="true">50.</strong> Spring Boot 11 - Web App 更新期間的維護模式：從唯讀到全鎖的解決方案</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/springBootLesson/spring-data-relation.html"><strong aria-hidden="true">51.</strong> Spring Data 關聯型態 一對多 多對一</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/springBootLesson/spring-boot-test-principle.html"><strong aria-hidden="true">52.</strong> Spring Boot Web 測試案例編寫方向</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/springBootLesson/spring-web-api-async.md.html"><strong aria-hidden="true">53.</strong> Spring Boot Web 異步 Api</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/springBootLesson/spring-boot-email.html"><strong aria-hidden="true">54.</strong> Spring Boot Email</a></span></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="spring/springBootLesson/draft-spring-web-structure.html"><strong aria-hidden="true">55.</strong> draft structure</a></span></li><li class="chapter-item expanded "><li class="part-title">vue js</li></li><li class="chapter-item expanded "><span class="chapter-link-wrapper"><a href="vuejs/TimeAttack.html"><strong aria-hidden="true">56.</strong> draft time attact</a></span></li></ol>';
        // Set the current, active page, and reveal it if it's hidden
        let current_page = document.location.href.toString().split('#')[0].split('?')[0];
        if (current_page.endsWith('/')) {
            current_page += 'index.html';
        }
        const links = Array.prototype.slice.call(this.querySelectorAll('a'));
        const l = links.length;
        for (let i = 0; i < l; ++i) {
            const link = links[i];
            const href = link.getAttribute('href');
            if (href && !href.startsWith('#') && !/^(?:[a-z+]+:)?\/\//.test(href)) {
                link.href = path_to_root + href;
            }
            // The 'index' page is supposed to alias the first chapter in the book.
            if (link.href === current_page
                || i === 0
                && path_to_root === ''
                && current_page.endsWith('/index.html')) {
                link.classList.add('active');
                let parent = link.parentElement;
                while (parent) {
                    if (parent.tagName === 'LI' && parent.classList.contains('chapter-item')) {
                        parent.classList.add('expanded');
                    }
                    parent = parent.parentElement;
                }
            }
        }
        // Track and set sidebar scroll position
        this.addEventListener('click', e => {
            if (e.target.tagName === 'A') {
                sessionStorage.setItem('sidebar-scroll', this.scrollTop);
            }
        }, { passive: true });
        const sidebarScrollTop = sessionStorage.getItem('sidebar-scroll');
        sessionStorage.removeItem('sidebar-scroll');
        if (sidebarScrollTop) {
            // preserve sidebar scroll position when navigating via links within sidebar
            this.scrollTop = sidebarScrollTop;
        } else {
            // scroll sidebar to current active section when navigating via
            // 'next/previous chapter' buttons
            const activeSection = document.querySelector('#mdbook-sidebar .active');
            if (activeSection) {
                activeSection.scrollIntoView({ block: 'center' });
            }
        }
        // Toggle buttons
        const sidebarAnchorToggles = document.querySelectorAll('.chapter-fold-toggle');
        function toggleSection(ev) {
            ev.currentTarget.parentElement.parentElement.classList.toggle('expanded');
        }
        Array.from(sidebarAnchorToggles).forEach(el => {
            el.addEventListener('click', toggleSection);
        });
    }
}
window.customElements.define('mdbook-sidebar-scrollbox', MDBookSidebarScrollbox);


// ---------------------------------------------------------------------------
// Support for dynamically adding headers to the sidebar.

(function() {
    // This is used to detect which direction the page has scrolled since the
    // last scroll event.
    let lastKnownScrollPosition = 0;
    // This is the threshold in px from the top of the screen where it will
    // consider a header the "current" header when scrolling down.
    const defaultDownThreshold = 150;
    // Same as defaultDownThreshold, except when scrolling up.
    const defaultUpThreshold = 300;
    // The threshold is a virtual horizontal line on the screen where it
    // considers the "current" header to be above the line. The threshold is
    // modified dynamically to handle headers that are near the bottom of the
    // screen, and to slightly offset the behavior when scrolling up vs down.
    let threshold = defaultDownThreshold;
    // This is used to disable updates while scrolling. This is needed when
    // clicking the header in the sidebar, which triggers a scroll event. It
    // is somewhat finicky to detect when the scroll has finished, so this
    // uses a relatively dumb system of disabling scroll updates for a short
    // time after the click.
    let disableScroll = false;
    // Array of header elements on the page.
    let headers;
    // Array of li elements that are initially collapsed headers in the sidebar.
    // I'm not sure why eslint seems to have a false positive here.
    // eslint-disable-next-line prefer-const
    let headerToggles = [];
    // This is a debugging tool for the threshold which you can enable in the console.
    let thresholdDebug = false;

    // Updates the threshold based on the scroll position.
    function updateThreshold() {
        const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
        const windowHeight = window.innerHeight;
        const documentHeight = document.documentElement.scrollHeight;

        // The number of pixels below the viewport, at most documentHeight.
        // This is used to push the threshold down to the bottom of the page
        // as the user scrolls towards the bottom.
        const pixelsBelow = Math.max(0, documentHeight - (scrollTop + windowHeight));
        // The number of pixels above the viewport, at least defaultDownThreshold.
        // Similar to pixelsBelow, this is used to push the threshold back towards
        // the top when reaching the top of the page.
        const pixelsAbove = Math.max(0, defaultDownThreshold - scrollTop);
        // How much the threshold should be offset once it gets close to the
        // bottom of the page.
        const bottomAdd = Math.max(0, windowHeight - pixelsBelow - defaultDownThreshold);
        let adjustedBottomAdd = bottomAdd;

        // Adjusts bottomAdd for a small document. The calculation above
        // assumes the document is at least twice the windowheight in size. If
        // it is less than that, then bottomAdd needs to be shrunk
        // proportional to the difference in size.
        if (documentHeight < windowHeight * 2) {
            const maxPixelsBelow = documentHeight - windowHeight;
            const t = 1 - pixelsBelow / Math.max(1, maxPixelsBelow);
            const clamp = Math.max(0, Math.min(1, t));
            adjustedBottomAdd *= clamp;
        }

        let scrollingDown = true;
        if (scrollTop < lastKnownScrollPosition) {
            scrollingDown = false;
        }

        if (scrollingDown) {
            // When scrolling down, move the threshold up towards the default
            // downwards threshold position. If near the bottom of the page,
            // adjustedBottomAdd will offset the threshold towards the bottom
            // of the page.
            const amountScrolledDown = scrollTop - lastKnownScrollPosition;
            const adjustedDefault = defaultDownThreshold + adjustedBottomAdd;
            threshold = Math.max(adjustedDefault, threshold - amountScrolledDown);
        } else {
            // When scrolling up, move the threshold down towards the default
            // upwards threshold position. If near the bottom of the page,
            // quickly transition the threshold back up where it normally
            // belongs.
            const amountScrolledUp = lastKnownScrollPosition - scrollTop;
            const adjustedDefault = defaultUpThreshold - pixelsAbove
                + Math.max(0, adjustedBottomAdd - defaultDownThreshold);
            threshold = Math.min(adjustedDefault, threshold + amountScrolledUp);
        }

        if (documentHeight <= windowHeight) {
            threshold = 0;
        }

        if (thresholdDebug) {
            const id = 'mdbook-threshold-debug-data';
            let data = document.getElementById(id);
            if (data === null) {
                data = document.createElement('div');
                data.id = id;
                data.style.cssText = `
                    position: fixed;
                    top: 50px;
                    right: 10px;
                    background-color: 0xeeeeee;
                    z-index: 9999;
                    pointer-events: none;
                `;
                document.body.appendChild(data);
            }
            data.innerHTML = `
                <table>
                  <tr><td>documentHeight</td><td>${documentHeight.toFixed(1)}</td></tr>
                  <tr><td>windowHeight</td><td>${windowHeight.toFixed(1)}</td></tr>
                  <tr><td>scrollTop</td><td>${scrollTop.toFixed(1)}</td></tr>
                  <tr><td>pixelsAbove</td><td>${pixelsAbove.toFixed(1)}</td></tr>
                  <tr><td>pixelsBelow</td><td>${pixelsBelow.toFixed(1)}</td></tr>
                  <tr><td>bottomAdd</td><td>${bottomAdd.toFixed(1)}</td></tr>
                  <tr><td>adjustedBottomAdd</td><td>${adjustedBottomAdd.toFixed(1)}</td></tr>
                  <tr><td>scrollingDown</td><td>${scrollingDown}</td></tr>
                  <tr><td>threshold</td><td>${threshold.toFixed(1)}</td></tr>
                </table>
            `;
            drawDebugLine();
        }

        lastKnownScrollPosition = scrollTop;
    }

    function drawDebugLine() {
        if (!document.body) {
            return;
        }
        const id = 'mdbook-threshold-debug-line';
        const existingLine = document.getElementById(id);
        if (existingLine) {
            existingLine.remove();
        }
        const line = document.createElement('div');
        line.id = id;
        line.style.cssText = `
            position: fixed;
            top: ${threshold}px;
            left: 0;
            width: 100vw;
            height: 2px;
            background-color: red;
            z-index: 9999;
            pointer-events: none;
        `;
        document.body.appendChild(line);
    }

    function mdbookEnableThresholdDebug() {
        thresholdDebug = true;
        updateThreshold();
        drawDebugLine();
    }

    window.mdbookEnableThresholdDebug = mdbookEnableThresholdDebug;

    // Updates which headers in the sidebar should be expanded. If the current
    // header is inside a collapsed group, then it, and all its parents should
    // be expanded.
    function updateHeaderExpanded(currentA) {
        // Add expanded to all header-item li ancestors.
        let current = currentA.parentElement;
        while (current) {
            if (current.tagName === 'LI' && current.classList.contains('header-item')) {
                current.classList.add('expanded');
            }
            current = current.parentElement;
        }
    }

    // Updates which header is marked as the "current" header in the sidebar.
    // This is done with a virtual Y threshold, where headers at or below
    // that line will be considered the current one.
    function updateCurrentHeader() {
        if (!headers || !headers.length) {
            return;
        }

        // Reset the classes, which will be rebuilt below.
        const els = document.getElementsByClassName('current-header');
        for (const el of els) {
            el.classList.remove('current-header');
        }
        for (const toggle of headerToggles) {
            toggle.classList.remove('expanded');
        }

        // Find the last header that is above the threshold.
        let lastHeader = null;
        for (const header of headers) {
            const rect = header.getBoundingClientRect();
            if (rect.top <= threshold) {
                lastHeader = header;
            } else {
                break;
            }
        }
        if (lastHeader === null) {
            lastHeader = headers[0];
            const rect = lastHeader.getBoundingClientRect();
            const windowHeight = window.innerHeight;
            if (rect.top >= windowHeight) {
                return;
            }
        }

        // Get the anchor in the summary.
        const href = '#' + lastHeader.id;
        const a = [...document.querySelectorAll('.header-in-summary')]
            .find(element => element.getAttribute('href') === href);
        if (!a) {
            return;
        }

        a.classList.add('current-header');

        updateHeaderExpanded(a);
    }

    // Updates which header is "current" based on the threshold line.
    function reloadCurrentHeader() {
        if (disableScroll) {
            return;
        }
        updateThreshold();
        updateCurrentHeader();
    }


    // When clicking on a header in the sidebar, this adjusts the threshold so
    // that it is located next to the header. This is so that header becomes
    // "current".
    function headerThresholdClick(event) {
        // See disableScroll description why this is done.
        disableScroll = true;
        setTimeout(() => {
            disableScroll = false;
        }, 100);
        // requestAnimationFrame is used to delay the update of the "current"
        // header until after the scroll is done, and the header is in the new
        // position.
        requestAnimationFrame(() => {
            requestAnimationFrame(() => {
                // Closest is needed because if it has child elements like <code>.
                const a = event.target.closest('a');
                const href = a.getAttribute('href');
                const targetId = href.substring(1);
                const targetElement = document.getElementById(targetId);
                if (targetElement) {
                    threshold = targetElement.getBoundingClientRect().bottom;
                    updateCurrentHeader();
                }
            });
        });
    }

    // Takes the nodes from the given head and copies them over to the
    // destination, along with some filtering.
    function filterHeader(source, dest) {
        const clone = source.cloneNode(true);
        clone.querySelectorAll('mark').forEach(mark => {
            mark.replaceWith(...mark.childNodes);
        });
        dest.append(...clone.childNodes);
    }

    // Scans page for headers and adds them to the sidebar.
    document.addEventListener('DOMContentLoaded', function() {
        const activeSection = document.querySelector('#mdbook-sidebar .active');
        if (activeSection === null) {
            return;
        }

        const main = document.getElementsByTagName('main')[0];
        headers = Array.from(main.querySelectorAll('h2, h3, h4, h5, h6'))
            .filter(h => h.id !== '' && h.children.length && h.children[0].tagName === 'A');

        if (headers.length === 0) {
            return;
        }

        // Build a tree of headers in the sidebar.

        const stack = [];

        const firstLevel = parseInt(headers[0].tagName.charAt(1));
        for (let i = 1; i < firstLevel; i++) {
            const ol = document.createElement('ol');
            ol.classList.add('section');
            if (stack.length > 0) {
                stack[stack.length - 1].ol.appendChild(ol);
            }
            stack.push({level: i + 1, ol: ol});
        }

        // The level where it will start folding deeply nested headers.
        const foldLevel = 3;

        for (let i = 0; i < headers.length; i++) {
            const header = headers[i];
            const level = parseInt(header.tagName.charAt(1));

            const currentLevel = stack[stack.length - 1].level;
            if (level > currentLevel) {
                // Begin nesting to this level.
                for (let nextLevel = currentLevel + 1; nextLevel <= level; nextLevel++) {
                    const ol = document.createElement('ol');
                    ol.classList.add('section');
                    const last = stack[stack.length - 1];
                    const lastChild = last.ol.lastChild;
                    // Handle the case where jumping more than one nesting
                    // level, which doesn't have a list item to place this new
                    // list inside of.
                    if (lastChild) {
                        lastChild.appendChild(ol);
                    } else {
                        last.ol.appendChild(ol);
                    }
                    stack.push({level: nextLevel, ol: ol});
                }
            } else if (level < currentLevel) {
                while (stack.length > 1 && stack[stack.length - 1].level > level) {
                    stack.pop();
                }
            }

            const li = document.createElement('li');
            li.classList.add('header-item');
            li.classList.add('expanded');
            if (level < foldLevel) {
                li.classList.add('expanded');
            }
            const span = document.createElement('span');
            span.classList.add('chapter-link-wrapper');
            const a = document.createElement('a');
            span.appendChild(a);
            a.href = '#' + header.id;
            a.classList.add('header-in-summary');
            filterHeader(header.children[0], a);
            a.addEventListener('click', headerThresholdClick);
            const nextHeader = headers[i + 1];
            if (nextHeader !== undefined) {
                const nextLevel = parseInt(nextHeader.tagName.charAt(1));
                if (nextLevel > level && level >= foldLevel) {
                    const toggle = document.createElement('a');
                    toggle.classList.add('chapter-fold-toggle');
                    toggle.classList.add('header-toggle');
                    toggle.addEventListener('click', () => {
                        li.classList.toggle('expanded');
                    });
                    const toggleDiv = document.createElement('div');
                    toggleDiv.textContent = '❱';
                    toggle.appendChild(toggleDiv);
                    span.appendChild(toggle);
                    headerToggles.push(li);
                }
            }
            li.appendChild(span);

            const currentParent = stack[stack.length - 1];
            currentParent.ol.appendChild(li);
        }

        const onThisPage = document.createElement('div');
        onThisPage.classList.add('on-this-page');
        onThisPage.append(stack[0].ol);
        const activeItemSpan = activeSection.parentElement;
        activeItemSpan.after(onThisPage);
    });

    document.addEventListener('DOMContentLoaded', reloadCurrentHeader);
    document.addEventListener('scroll', reloadCurrentHeader, { passive: true });
})();

