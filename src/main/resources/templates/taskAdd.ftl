<#include "masters/mainMenu.ftl">
<@menu title="Main page">
<div class="panel panel-default" style="margin-top:45px">
    <div class="panel-heading">
        <h3 class="panel-title">Добавление задания</h3>
    </div>
    <div class="panel-body">

    <#if error??>
    <div class="alert alert-danger" role="alert">${error}</div>
    </#if>

        <form method="post">
            <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
            <div class="form-group">
                <label for="group">Имя группы</label>
                <select class="form-control" name="group_id" id="group">
                    <#list groups as group>
                        <option value="${group.id}">${group.name}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label for="test">Имя теста</label>
                <select class="form-control" name="test_id" id="test">
                    <#list tests as test>
                        <option value="${test.id}">${test.name}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label for="end_time">Время окончания</label>
                <input type="datetime-local" class="form-control" id="end_time"
                       name="end_time">
            </div>
            <button type="submit" class="btn btn-default">Создать</button>
        </form>
    </div>
</div>
</@menu>

