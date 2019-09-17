<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

</head>
<body>
<div class="travel-index-nav">
    <div class="citylistbox">
    <#list queryResult.list as ch>
        <div class="listbox">
            <div class="list">
                <dl>
                    <dt><a href="#">${ch.categoryName}</a></dt>
                </dl>
            </div>
            <div class="box">
                <ul class="mod-nav__side-list">
                    <#list ch.children as ch1>
                        <li class="mod-nav__side-li" jump-through="1">
                            <dl>
                                <dt><h5 class="mod-nav__link-nav-second"><a
                                    href="https://ke.qq.com/course/list?mt=1001&amp;st=2001" title="${ch1.categoryName}"
                                    class="mod-nav__link-nav-second-link" target="_blank"
                                    report-tdw="action=click&amp;obj1=second_level&amp;obj2=2001"
                                    jump-start="title_second">${ch1.categoryName}</a></h5></dt>
                                <dd>
                                    <div class="mod-nav__wrap-nav-third">
                                        <#list ch1.children as ch2>
                                            <a href="course_list.html"
                                               class="mod-nav__link-nav-third mod-nav__wrap-nav-third_line"
                                               title="${ch2.categoryName}"
                                               target="_blank"
                                               report-tdw="action=click&amp;obj1=third_level&amp;obj2=3001"
                                               jump-start="title_third" jump-through="1">${ch2.categoryName}
                                            </a>
                                        </#list>
                                    </div>
                                </dd>
                            </dl>
                        </li>
                    </#list>
                </ul>
            </div>
        </div>
    </#list>
    </div>
</div>
</body>
</html>