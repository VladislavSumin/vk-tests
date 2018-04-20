<#macro menu title>

<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <#include "bootstrap.ftl">
</head>

<body>
    <#include "header.ftl">
<#nested>
    <#include "footer.ftl">
</body>

</html>

</#macro>