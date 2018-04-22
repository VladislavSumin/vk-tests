<#include "masters/mainMenu.ftl">
<@menu title="Main page">

<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading">Список написавших</div>

        <table class="table">
            <thead>
            <tr>
                <th>#</th>
                <th>Фамилия</th>
                <th>Имя</th>
                <th>Отчество</th>
                <th>Результат</th>
            </tr>
            </thead>
            <tbody>
            <#assign x = 1>
            <#list task.taskResults as result>
            <#--TODO пофиксить жирный текст-->
            <tr>
                <th scope="row">${x}</th>
                <th style="font-weight:300">${result.user.lastName}</th>
                <th style="font-weight:300">${result.user.firstName}</th>
                <th style="font-weight:300">${result.user.soname}</th>
                <th style="font-weight:300">${result.rightAnswers} / ${result.countQuestions}</th>
            </tr>
                <#assign x++>
            </#list>
            </tbody>
        </table>
    </div>
</div>

<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading">Список не написавших</div>

        <table class="table">
            <thead>
            <tr>
                <th>#</th>
                <th>Фамилия</th>
                <th>Имя</th>
                <th>Отчество</th>
            </tr>
            </thead>
            <tbody>
            <#assign x = 1>
            <#list task.group.users as user>
            <#--TODO пофиксить жирный текст-->
            <tr>
                <th scope="row">${x}</th>
                <th style="font-weight:300">${user.lastName}</th>
                <th style="font-weight:300">${user.firstName}</th>
                <th style="font-weight:300">${user.soname}</th>
            </tr>
                <#assign x++>
            </#list>
            </tbody>
        </table>
    </div>
</div>
</@menu>

