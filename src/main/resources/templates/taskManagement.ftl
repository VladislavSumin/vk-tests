<#include "masters/mainMenu.ftl">
<@menu title="Main page">
<div class="row">
    <div class="btn-group" role="group" aria-label="...">
        <button onclick="window.location.href='/admin/task/add'" type="button" class="btn btn-default">Новое задание</button>
    </div>
</div>
<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading">Список заданий</div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th>#</th>
                <th>Группа</th>
                <th>Тест</th>
                <th>Время окончания</th>
            </tr>
            </thead>
            <tbody>
            <#assign x = 1>
            <#list tasks as task>
            <#--TODO пофиксить жирный текст-->
            <tr onclick="window.location.href='/admin/task/info/${task.id}'">
                <th scope="row">${x}</th>
                <th style="font-weight:300">${task.group.name}</th>
                <th style="font-weight:300">${task.test.name}</th>
                <th style="font-weight:300">${task.endTime}</th>
            </tr>
                <#assign x++>
            </#list>
            </tbody>
        </table>
    </div>
</div>
</@menu>

