/* toolbar-1.0.0 main.js Date:2017-08-24 19:35:02 */
seajs.config({
    paths: {
        TB_ROOT: "//static.360buyimg.com/devfe/toolbar/1.0.0",
        JDF_UI: "//misc.360buyimg.com/jdf/1.0.0/ui",
        JDF_UNIT: "//misc.360buyimg.com/jdf/1.0.0/unit"
    }
}),
    define("TB_ROOT/js/main", function(require, a, b) {
        require("JDF_UNIT/trimPath/1.0.0/trimPath.js");
        var d = require("JDF_UNIT/login/1.0.0/login.js");
        require("TB_ROOT/widget/common/common.css");
        var f = require("TB_ROOT/js/localStorageObj.js").localStorage;
        var g = {
            $el: $("#J-global-toolbar"),
            pType: "",
            enabled: !0,
            bars: {
                jdvip: {
                    index: .3,
                    enabled: !0,
                    title: "\u4eac\u4e1c\u4f1a\u5458",
                    login: !0,
                    vip: "1",
                    iframe: "//vip.jd.com/sideBar/index.html"
                },
                cart: {
                    index: 1,
                    enabled: !0,
                    title: "\u8d2d\u7269\u8f66",
                    href: "//cart.jd.com/cart.action?r=" + Math.random(),
                    js: "TB_ROOT/widget/cart/cart",
                    css: "TB_ROOT/widget/cart/cart.css"
                },
                follow: {
                    index: 2,
                    enabled: !0,
                    title: "\u6211\u7684\u5173\u6ce8",
                    login: !0,
                    href: "//t.jd.com/home/follow",
                    js: "TB_ROOT/widget/follow/follow",
                    css: "TB_ROOT/widget/follow/follow.css"
                },
                history: {
                    index: 3,
                    enabled: !0,
                    title: "\u6211\u7684\u8db3\u8ff9",
                    href: "//my.jd.com/history/list.html",
                    js: "TB_ROOT/widget/history/history",
                    css: "TB_ROOT/widget/history/history.css"
                },
                message: {
                    index: 4,
                    enabled: !0,
                    title: "\u6211\u7684\u6d88\u606f",
                    target: "//joycenter.jd.com/msgCenter/queryHistoryMessage.action"
                },
                jimi: {
                    index: 5,
                    enabled: !0,
                    title: "\u54a8\u8be2JIMI",
                    login: !0,
                    iframe: "//jimi.jd.com/index.action?source=jdhome"
                }
            },
            links: {
                top: {
                    index: 1,
                    title: "\u9876\u90e8",
                    anchor: "#shortcut-2013"
                },
                feedback: {
                    index: 2,
                    title: "\u53cd\u9988",
                    href: "//diaoyan.jd.com/survey/answerSurvey.htm?eSurveyId=AZDFYX5KJGMOK"
                }
            },
            ad: {
                enabled: !0,
                id: "0_0_7209",
                startTime: +new Date(2017,0,4,0,0,1) / 1e3,
                endTime: +new Date(2017,1,4,0,0,0) / 1e3
            }
        };
        var h = function(a) {
            return "mousewheel" === a.type ? a.wheelDelta > 0 ? "up" : "down" : "DOMMouseScroll" === a.type ? a.detail > 0 ? "down" : "up" : "unknown"
        };
        var i = function(a) {
            return 0 === a.scrollTop() ? "top" : a.scrollTop() + a.outerHeight() >= a[0].scrollHeight ? "bottom" : "middle"
        };
        var j = function(a) {
            return a[0].scrollHeight > a.innerHeight()
        };
        pageConfig.__toolbarLogin = function(a) {
            seajs.use("JDF_UNIT/login/1.0.0/login", function(b) {
                b({
                    modal: !0,
                    complete: function(b) {
                        a(b)
                    }
                })
            })
        }
        ;
        function k(a) {
            this.settings = $.extend(!0, {}, g, a),
                this.onOpen = a.onOpen || function() {}
                ,
                this.onClose = a.onClose || function() {}
                ,
                this.onSwitch = a.onSwitch || function() {}
                ,
                this.onLogin = a.onLogin || function() {}
                ,
            this.settings.links.top.href && delete this.settings.links.top.anchor,
            this.settings.links.feedback.anchor && delete this.settings.links.feedback.href,
                this.init()
        }
        k.prototype = {
            init: function() {
                return this.settings.enabled ? (this.$w = $(window),
                    this.$d = $(document),
                    this.isIE6 = $.browser.isIE6(),
                    this.settings.clsPageType = this.getPageType(),
                    this.render(),
                    this.bindEvent(),
                    this.opened = !1,
                    this.triggerClick = "z-jdm-tbar-tab-selected",
                    this.triggerHover = "z-jdm-tbar-tab-hover",
                    this.toolbarOpen = "z-jdm-toolbar-open",
                    this.$toolbar = this.settings.$el.find(".J-toolbar"),
                    this.$wrap = this.settings.$el.find(".J-wrap"),
                    this.$trigger = this.settings.$el.find('.J-trigger[data-type="bar"]'),
                    this.$content = this.settings.$el.find(".J-content"),
                    this.$newItemInCartHint = this.settings.$el.find(".jdm-tbar-tab-cart .tabs-tip"),
                    this.setBubbleCount("cart", readCookie("cn")),
                this.isIE6 && (this.resetLayout("height"),
                    this.resetLayout("top"),
                    this.resetLayout("right")),
                    this.settings.$el.find("#J-toolbar-load-hook").trigger("click"),
                    this.eventDispatcher = $({}),
                this.settings.ad.enabled && this.insertAD(),
                    this.bubbleKey = "toolbar_bubble",
                    void ("home" == this.settings.pType && this.setGiftBubble())) : !1
            },
            setGiftBubble: function() {
                var a = this;
                d.isLogin(function(b) {
                    b && a.setLocalStorage() && a.createGiftBubble()
                })
            },
            setLocalStorage: function() {
                var a = this;
                return f.check(a.bubbleKey)
            },
            createGiftBubble: function() {
                var a = this;
                var b = a.settings.$el.find(".jdm-toolbar-tabs");
                $.ajax({
                    url: "//vip.jd.com/gift/getWaitReceiveGift.html",
                    scriptCharset: "utf-8",
                    dataType: "jsonp",
                    success: function(c) {
                        var d = ""
                            , e = c.result.gifts;
                        c.success && e.length && (d = 1 == e.length ? '<div class="poptip"><i class="giftMsg"></i><b class="giftTxt">\u60a8\u6709\u4e00\u4e2a' + e[0].name + '\u5f85\u9886\u53d6\uff01</b><em class="giftClose"></em><span class="poptip-arrow poptip-arrow-right"><em>\u25c6</em><i>\u25c6</i></span></div>' : '<div class="poptip"><i class="giftMsg"></i><b class="giftTxt">\u4f60\u6709' + e.length + '\u4e2a\u793c\u5305\u5f85\u9886\u53d6\uff01</b><em class="giftClose"></em><span class="poptip-arrow poptip-arrow-right"><em>\u25c6</em><i>\u25c6</i></span></div>',
                            b.append(d),
                            $(".poptip .giftClose").unbind("click").bind("click", function() {
                                return $(".poptip").fadeOut(300, function() {
                                    $(".poptip").remove(),
                                        f.set(a.bubbleKey)
                                }),
                                    !1
                            }),
                            $(".poptip").unbind("click").bind("click", function() {
                                $(".jdm-tbar-tab-jdvip").trigger("click"),
                                    $(".poptip").fadeOut(300, function() {
                                        $(".poptip").remove()
                                    })
                            }))
                    }
                })
            },
            getPageType: function() {
                var a = "h";
                switch (this.settings.pType) {
                    case "home":
                        a = "h";
                        break;
                    case "list":
                        a = "thirdtype";
                        break;
                    case "search":
                        a = "thirdtype";
                        break;
                    case "item":
                        a = "shangpin";
                        break;
                    default:
                        a = "h"
                }
                return a
            },
            insertAD: function() {
                try {
                    if (this.settings.ad && this.settings.ad.startTime && this.settings.ad.id) {
                        this.settings.ad.endTime || (this.settings.ad.endTime = this.settings.ad.startTime + 7776e3);
                        var a;
                        var b;
                        var c = (new Date).valueOf() / 1e3;
                        window.pageConfig && window.pageConfig.timestamp ? (b = window.pageConfig.timestamp / 1e3,
                            a = 300 > c - b && c - b > 0 ? c : b) : a = c;
                        var d;
                        (d = window.location.search.match(/isdebugToolbarTime=(\d+)/)) && (a = parseInt(d[1], 10)),
                        a > this.settings.ad.startTime && a < this.settings.ad.endTime && this.loadAD()
                    } else
                        this.settings.ad = null
                } catch (e) {
                    this.settings.ad = null
                }
            },
            loadAD: function(a) {
                a = a ? a : this.settings.ad.id;
                var b = this;
                var c = "//nfa.jd.com/loadFa_toJson.js?aid=" + a;
                $.ajax({
                    url: c,
                    dataType: "script",
                    scriptCharset: "gbk",
                    success: function() {
                        b.settings.bars.ad = {
                            name: "ad",
                            iframe: b.$wrap.find(".jdm-tbar-panel-ad").attr("data-iframe")
                        }
                    }
                })
            },
            render: function() {
                var a = '            <div class="jdm-toolbar-wrap J-wrap">                <div class="jdm-toolbar J-toolbar">                    <div class="jdm-toolbar-panels J-panel">                    <div data-name="ad" class="J-content jdm-toolbar-panel jdm-tbar-panel-ad">                        <h3 class="jdm-tbar-panel-header J-panel-header">                            <a>                            <i></i>                            <em class="title"></em>                            </a>                            <span class="close-panel J-close"></span>                        </h3>                        <div class="jdm-tbar-panel-main">                            <div class="jdm-tbar-panel-content J-panel-content">                            </div>                        </div>                    </div>                    {for bar in bars}                    {if bar.enabled&&!bar.target}                        <div data-name="${bar.name}" class="J-content jdm-toolbar-panel jdm-tbar-panel-${bar.name}">                            <h3 class="jdm-tbar-panel-header J-panel-header">                                <{if bar.href}a href="${bar.href}" target="_blank"{else}span{/if} class="title" clstag="${clsPageType}|keycount|cebianlan_${clsPageType}_${bar.name}|title">                                <i></i>                                <em class="title">${bar.title}</em>                                </{if bar.href}a{else}span{/if}>                                <span class="close-panel J-close"></span>                            </h3>                            <div class="jdm-tbar-panel-main">                                <div class="jdm-tbar-panel-content J-panel-content" {if bar.iframe}style="overflow:hidden;"{/if}>                                <div class="jdm-tbar-tipbox2">                                    <div class="tip-inner">                                        <i class="i-loading"></i>                                    </div>                                </div>                                </div>                            </div>                            <div class="jdm-tbar-panel-footer J-panel-footer"></div>                        </div>                    {/if}                    {/for}                    </div>                    <div class="jdm-toolbar-header">                        <div class="jdm-tbar-act J-trigger" data-type="bar" data-name="ad" data-iframe="true"                             clstag="${clsPageType}|keycount|cebianlan_${clsPageType}_header|">                        </div>                    </div>                    <div class="jdm-toolbar-tabs J-tab">                    {for bar in bars}                    {if bar.enabled}                        <div {if !bar.target} data-type="bar" {/if}                            clstag="${clsPageType}|keycount|cebianlan_${clsPageType}_${bar.name}|btn"                             class="J-trigger jdm-toolbar-tab jdm-tbar-tab-${bar.name}"                             data-name="${bar.name}"                            {if bar.login}data-login="${bar.login}"{/if}                            {if bar.iframe}data-iframe="${bar.iframe}"{/if}>                                                        {if bar.target}<a target="_blank" href="${bar.target}">{/if}                            {if bar.vip}<i class="tab-tip"></i>{/if}                                <i class="tab-ico"></i>                                <em class="tab-text">                                    ${bar.title}                                </em>                                {if bar.extraHTML}${bar.extraHTML}{/if}                            {if bar.target}</a>{/if}                            <span class="tab-sub J-count hide">0</span>                            <div class="tabs-tip hide">                                <span class="ico"></span>                                <span class="text">\u6210\u529f\u52a0\u5165\u8d2d\u7269\u8f66!</span>                                <b></b>                            </div>                        </div>                    {/if}                    {/for}                    </div>                    <div class="jdm-toolbar-footer">                    {for link in links}                        <div data-type="link" class="J-trigger jdm-toolbar-tab jdm-tbar-tab-${link.name}">                            {if link.anchor}                                <a href="${link.anchor}" clstag="${clsPageType}|keycount|cebianlan_${clsPageType}|${link.name}">                                    <i class="tab-ico"></i>                                    <em class="tab-text">${link.title}</em>                                    {if link.extraHTML}${link.extraHTML}{/if}                                </a>                            {/if}                            {if link.href}                                <a href="${link.href}" target="_blank" clstag="${clsPageType}|keycount|cebianlan_${clsPageType}|${link.name}">                                    <i class="tab-ico"></i>                                    <em class="tab-text">${link.title}</em>                                    {if link.extraHTML}${link.extraHTML}{/if}                                </a>                            {/if}                        </div>                    {/for}                    </div>                    <div class="jdm-toolbar-mini">                    </div>                </div>                <div id="J-toolbar-load-hook" clstag="${clsPageType}|keycount|cebianlan_${clsPageType}|load"></div>            </div>';
                var b = this.sortJsonToArray(this.settings);
                b.clsPageType = this.getPageType(),
                    b.ad = this.settings.ad;
                try {
                    this.settings.$el.html(a.process(b))
                } catch (c) {
                    console.log("Toolbar rendered error >> " + c)
                }
            },
            sortJsonToArray: function(a) {
                var b = [];
                var c = [];
                for (var d in a.links)
                    a.links.hasOwnProperty(d) && (a.links[d].name = d,
                        c.push(a.links[d]));
                for (var e in a.bars)
                    a.bars.hasOwnProperty(e) && (a.bars[e].name = e,
                        b.push(a.bars[e]));
                function f(a, b) {
                    return a.index > b.index ? 1 : -1
                }
                return {
                    enabled: this.settings.enabled,
                    ad: this.settings.ad,
                    bars: b.sort(f),
                    links: c.sort(f),
                    clsPageType: this.clsPageType
                }
            },
            setBubbleCount: function(a, b) {
                var c = this.$trigger.filter('[data-name="' + a + '"]').find(".J-count");
                var d;
                b > 0 ? (d = b > 99 ? "99+" : b,
                    c.html(d).show()) : c.hide()
            },
            updateLayout: function() {
                var a = $(window).height();
                var b = this.$content.eq(this.m.index);
                var c = b.find(".J-panel-header").outerHeight();
                var d = b.find(".J-panel-footer").outerHeight();
                var e = b.find(".J-panel-content");
                e.css("height", a - c - d)
            },
            bindEvent: function() {
                var a = this;
                $(window).unbind("resize.toolbar").bind("resize.toolbar", function() {
                    a.opened && a.updateLayout()
                }),
                    $(document).undelegate("click.toolbar").delegate("body", "click.toolbar", function(b) {
                        $.contains(a.$wrap[0], b.target) || a.close()
                    }),
                    this.settings.$el.delegate(".J-trigger", "mouseenter", function() {
                        a.handleHover(!0, $(this)),
                        $(this).hasClass("jdm-tbar-tab-jdvip") && $(".poptip").length && $(".poptip").fadeOut(300, function() {
                            $(".poptip").hide()
                        })
                    });
                var b = null;
                this.settings.$el.delegate(".J-trigger", "mouseleave", function() {
                    a.handleHover(!1, $(this)),
                    $(this).hasClass("jdm-tbar-tab-jdvip") && $(".poptip").length && !a.opened && (clearTimeout(b),
                        b = setTimeout(function() {
                            $(".poptip").fadeIn(300, function() {
                                $(".poptip").show()
                            })
                        }, 300))
                }),
                    this.settings.$el.delegate(".J-trigger", "click", function() {
                        $(this).hasClass("jdm-tbar-tab-jdvip") && $(".poptip").length && $(".poptip").fadeOut(300, function() {
                            $(".poptip").remove()
                        }),
                            a.handleTrigger($(this))
                    }),
                    this.settings.$el.delegate(".J-close", "click", function() {
                        a.close()
                    }),
                    this.settings.$el.delegate(".J-panel-content", "mousewheel DOMMouseScroll", function(a) {
                        var b = $(this);
                        var c = h(a);
                        var d = i(b);
                        var e = j(b);
                        (!e || f() || g()) && a.preventDefault();
                        function f() {
                            return "up" === c && "top" === d
                        }
                        function g() {
                            return "down" === c && "bottom" === d
                        }
                    }),
                this.isIE6 && (this.$w.bind("resize", function() {
                    a.resetLayout("height"),
                        a.resetLayout("right")
                }),
                    this.$w.bind("scroll", function() {
                        a.resetLayout("top")
                    }))
            },
            resetLayout: function(a) {
                if ("height" === a) {
                    var b = this.$w.height();
                    this.$toolbar.add(this.$wrap).css("height", b)
                }
                if ("top" === a) {
                    var c = this.$d.scrollTop();
                    this.$wrap.css("top", c)
                }
                "right" === a && (this.$w.width() % 2 > 0 ? this.$wrap.css("right", -1) : this.$wrap.css("right", 0))
            },
            handleHover: function(a, b) {
                var c = this;
                b.parent().hasClass("jdm-toolbar-header") || (a ? b.addClass(c.triggerHover) : b.removeClass(c.triggerHover))
            },
            handleTrigger: function(a) {
                var b = this;
                var c = this.$trigger.index(a);
                var d = "bar" === a.attr("data-type");
                var e = {
                    index: c,
                    login: a.attr("data-login"),
                    name: a.attr("data-name"),
                    iframe: a.attr("data-iframe")
                };
                return b.opened && c === b.$content.data("last") ? void b.close() : (this.m = e,
                    d ? void (e.login ? seajs.use("JDF_UNIT/login/1.0.0/login", function(a) {
                        a.isLogin(function(c) {
                            c ? b.open(e) : "true" === e.login ? a({
                                modal: !0,
                                firstCheck: !1,
                                complete: function() {
                                    b.open(e),
                                        b.onLogin(e)
                                }
                            }) : location.href = "//passport.jd.com/new/login.aspx?ReturnUrl=" + encodeURIComponent(e.login)
                        })
                    }) : this.open(e)) : !1)
            },
            switchTo: function(a) {
                this.eventDispatcher.trigger(a.name + "PanelOpen"),
                    this.$trigger.removeClass(this.triggerClick),
                this.$trigger.eq(a.index).parent().hasClass("jdm-toolbar-header") || this.$trigger.eq(a.index).addClass(this.triggerClick);
                var b = this.$content.data("last");
                b != a.index && (this.$content.css("visibility", "hidden"),
                    this.$content.eq(a.index).css("z-index", "2"),
                    this.$content.eq(a.index).css("visibility", "visible"),
                void 0 != b && (this.$content.eq(b).css("z-index", "1").css("visibility", "visible"),
                    this.$content.eq(b).removeClass("toolbar-animate-in").addClass("toolbar-animate-out")),
                    this.$content.eq(a.index).removeClass("toolbar-animate-out").addClass("toolbar-animate-in"),
                    this.$content.data("last", a.index),
                this.settings.bars[a.name].loaded || this.load(a),
                    this.updateLayout(a))
            },
            load: function(a) {
                var b = this;
                var c = this.$content.eq(a.index);
                var d = c.find(".J-panel-header");
                var e = c.find(".J-panel-content");
                var f = c.find(".J-panel-footer");
                if (a.iframe) {
                    var g = '<iframe frameborder="0" style="height:100%;width:100%;" width="100%" height="100%" src="' + this.settings.bars[a.name].iframe + '"></iframe>';
                    e.html(g),
                        this.settings.bars[a.name].loaded = !0
                } else {
                    var h = this.settings.bars[a.name].js;
                    var i = this.settings.bars[a.name].css;
                    seajs.use([h, i], function(c) {
                        c && new c({
                            $header: d,
                            $content: e,
                            $footer: f
                        }),
                            b.settings.bars[a.name].loaded = !0
                    })
                }
            },
            open: function(a) {
                this.$wrap.addClass(this.toolbarOpen),
                    this.opened ? this.onSwitch(a) : this.onOpen(a),
                    this.opened = !0,
                    this.switchTo(a)
            },
            close: function() {
                this.$wrap.removeClass(this.toolbarOpen),
                    this.$trigger.removeClass(this.triggerClick).removeClass(this.triggerHover),
                    this.opened = !1,
                    this.onClose()
            },
            setStatus: function(a, b, c) {
                c = c || "fd";
                var d = '            <div>                <div class="jdm-tbar-tipbox2">                    <div class="tip-inner">                        <i class="i-face-' + c + ' tip-face"></i>                        <div class="tip-text">                            {content}                        </div>                    </div>                </div>            <div>';
                a.html(d.replace("{content}", b))
            },
            newItemInCart: function() {
                var a = this.$trigger.filter('[data-name="cart"]').find(".J-count");
                var b;
                b = a.is(":visible") ? parseInt(a.text(), 10) : 0,
                    this.setBubbleCount("cart", b + 1),
                    this.$newItemInCartHint.stop().show().css({
                        opacity: 1
                    }).delay(800).fadeOut(600)
            },
            sendLog: function(a, b, c) {
                log("ce_bian_lan", "0000110", this.settings.pType, a, b, c)
            }
        },
            b.exports = k
    });
