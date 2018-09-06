<#macro menu title>
    <#include "header.ftl">

<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <#include "bootstrap.ftl">
</head>

<body>
    <@header/>
<div class="container-fluid">
    <div class="row">
        <#if user.group.role!="ROLE_USER">
            <#include "menu.ftl">
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <#else>
        <div class="main">
        </#if>
            <#nested>
        </div>
    </div>
</div>
<#--<#include "footer.ftl">-->
</body>

</html>

</#macro>