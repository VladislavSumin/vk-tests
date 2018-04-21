<#include "masters/mainMenu.ftl">
<@menu title="Main page">
<div class="row">
    <div class="btn-group" role="group" aria-label="...">
        <button onclick="window.location.href='/admin/test/add'" type="button" class="btn btn-default">Новый тест
        </button>
    </div>
</div>
<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading">Список тестов</div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th>#</th>
                <th>Теста группы</th>
                <th>Лимит времени</th>
            </tr>
            </thead>
            <tbody>
            <#assign x = 1>
            <#list tests as test>
            <#--TODO пофиксить жирный текст-->
            <tr onclick="window.location.href='/admin/group/info/${test.id}'">
                <th scope="row">${x}</th>
                <th style="font-weight:300">${test.name}</th>
                <th style="font-weight:300">${test.timeLimit}</th>
            </tr>
                <#assign x++>
            </#list>
            </tbody>
        </table>
    </div>
</div>
</@menu>

