$(function(){


    $('#loginform').submit(function(e){
        e.preventDefault();

        var submit_form = $( this );

        $.ajax(submit_form.attr('action'), {
            type:"POST",
            data: submit_form.serialize(),
            success:function(data, status){
                top.location.href = '/';
            },
            error: function(){
                $('#loginerror').show();
            }
        });
    });


});