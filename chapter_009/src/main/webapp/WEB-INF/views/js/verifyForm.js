function verifyForm() {
    var result = true;
    var form = document.forms[0];
    var alertMSG = "Please fill: ";
    for (var i = 0; i != form.elements.length; i++) {
        if (form.elements[i].type == "text" || form.elements[i].type == "email" || form.elements[i].type == "password") {
            if (form.elements[i].value == "") {
                result = false;
                alertMSG += form.elements[i].name;
                break;
            }
        }
    }
    if (!result) {
        alert(alertMSG);
        event.preventDefault();
    } else {
        document.forms[0].submit();
    }

}