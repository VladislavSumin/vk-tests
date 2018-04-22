<#macro header>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
        <#--This need to create = button on small screen size-->
            <#--<button type="button" class="navbar-toggle collapsed navbar-left" data-toggle="collapse" data-target="#sidebar"-->
                    <#--aria-expanded="false" aria-controls="navbar">-->
                <#--<span class="sr-only">Toggle navigation</span>-->
                <#--<span class="icon-bar"></span>-->
                <#--<span class="icon-bar"></span>-->
                <#--<span class="icon-bar"></span>-->
            <#--</button>-->
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Сайт тестирования военной кафедры</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/">${user.username} </a></li>
                <li><a href="/">${user.group.name}</a></li>
                <li><a href="/logout">Выход</a></li>
            </ul>
        </div>
    </div>
</nav>
</#macro>