/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    $('.add_phone').click(function(){
        $new_phone = $('#phones div:last').clone();
        $('#phones div:last').after($new_phone);
    });
    
    $('.remove_phone').click(function(){
        if($('#phones div').length > 1){
            $('#phones div:last').remove();
        }
    });
});