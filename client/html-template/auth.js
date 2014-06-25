/**
 * Created by Crabar on 22.05.2014.
 */

function initOAuthIO() {
    OAuth.initialize('w5lnaZLTxqd0EeBl99PrcUK3UBo');
}



function socialLogin(provider) {
    OAuth.popup(provider).done(function(result) {
        result.me().done(function(me) {
            alert(me.name);
            document.${application}.onSocialLogin(me);
        })
    });
}

function onSuccessGetProfile(result) {
    document.${application}.onSocialLogin(result);
}

function getProfile(result) {
    result.me().done(onSuccessGetProfile);
}

function sendRequest(method, address, queryParams, bodyParams, headers, expectedStatus, requestID) {
    var xmlhttp = new XMLHttpRequest();
    var target;

    if (queryParams != null)
        target = address + '?' + queryParams;
    else
        target = address;

    xmlhttp.open(method, target, true);

    if (headers != null) {
        for (var i = 0; i < headers.length; i++) {
            if (headers[i] != null)
                xmlhttp.setRequestHeader(headers[i].name, headers[i].value);
        }
    }

    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4) {
            if (xmlhttp.status == expectedStatus) {
                document.${application}.onRequestComplete(xmlhttp.responseText, requestID);
            }
            else if (xmlhttp.status == 401) {
                document.${application}.processAuth(xmlhttp.getResponseHeader('WWW-Authenticate'), address, requestID);
            }
            else {
                document.${application}.onError(xmlhttp.responseText);
            }
        }
    };

    xmlhttp.send(bodyParams);
}