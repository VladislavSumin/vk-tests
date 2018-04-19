<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Title</title>

    <#include "masters/bootstrap.ftl">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-default" style="margin-top:45px">
                <div class="panel-heading">
                    <h3 class="panel-title">Login with Username and Password</h3>
                </div>
                <div class="panel-body">
                <#if logout>
                    <div class="alert alert-info" role="alert">You've been logged out successfully.</div>
                </#if>
                <#if error>
                    <div class="alert alert-danger" role="alert">Invalid Username or Password!</div>
                </#if>

                    <form method="post">
                        <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" class="form-control" id="username" placeholder="Username"
                                   name="username">
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" placeholder="Password"
                                   name="password">
                        </div>
                        <button type="submit" class="btn btn-default">Log in</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>