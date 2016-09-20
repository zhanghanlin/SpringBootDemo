$(function () {
    $('body').delegate('div.modal','hide',function(){
        $(this).find('form').resetForm();
    });
});