/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

jQuery.fn.extend({
    validateName: function() {
        var $nameInput = $('input.name');
        var $nameSpan = $('span.name');
        var nameLength = $nameInput.val().length;

        if (nameLength === 0 || nameLength > 40) {
            $nameSpan.html("Name must have between 1 and 40 characters");
            $nameSpan.removeClass("success").addClass("error");
            $nameInput.removeClass("success").addClass("error");
            return false;
        }
        else {
            $nameSpan.html("");
            $nameSpan.removeClass("error").addClass("success");
            $nameInput.removeClass("error").addClass("success");
            return true;
        }
    }
});

jQuery.fn.extend({
    validateEmail: function() {
        var $emailInput = $('input.email');
        var $emailSpan = $('span.email');
        var email = $emailInput.val();
        var emailRegex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        if (!email.match(emailRegex)) {
            $emailSpan.html("Not a valid e-mail address");
            $emailSpan.removeClass("success").addClass("error");
            $emailInput.removeClass("success").addClass("error");
            return false;
        }
        else {
            $emailSpan.html("");
            $emailSpan.removeClass("error").addClass("success");
            $emailInput.removeClass("error").addClass("success");
            return true;
        }
    }
});

jQuery.fn.extend({
    validatePassword: function() {
        var $passwordInput = $('input.password');
        var $passwordSpan = $('span.password');
        var password = $passwordInput.val();

        if (password === null || password.length < 8 || password.length > 20) {
            $passwordSpan.html("Password must have between 8 and 20 characters");
            $passwordSpan.removeClass("success").addClass("error");
            $passwordInput.removeClass("success").addClass("error");
            return false;
        }
        else {
            $passwordSpan.html("");
            $passwordSpan.removeClass("error").addClass("success");
            $passwordInput.removeClass("error").addClass("success");
            return true;
        }
    }
});

jQuery.fn.extend({
    validatePhone: function() {
        $('div.phone').each(function() {
            var $phoneInput = $(this).children('input.phone');
            var $phoneSpan = $(this).children('span.phone');
            var phone = $phoneInput.val();

            if (!$.isNumeric(phone) || phone.length !== 11) {
                $phoneSpan.html("Phone must be a number with 11 digits");
                $phoneSpan.removeClass("success").addClass("error");
                $phoneInput.removeClass("success").addClass("error");
            }
            else {
                $phoneSpan.html("");
                $phoneSpan.removeClass("error").addClass("success");
                $phoneInput.removeClass("error").addClass("success");
            }
        });

        $('div.phone').each(function() {
            $(this).children('span').hasClass('error');
            return false;
        });
    }
});

$(document).ready(function() {
    $('form').submit(function() {
        var validName = $(this).validateName();
        var validEmail = $(this).validateEmail();
        var validPassword = $(this).validatePassword();
        var validPhone = $(this).validatePhone();

        return (validName && validEmail && validPassword && validPhone);
    });
});
