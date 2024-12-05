
$(document).ready(function ()
{
    $('#btnFullScreen').on("click", function (event) {

        $("#btnFullScreen img").toggleClass("hide");

        FullScreen(event)
    });
    
    
    
    $(".btnDelete").click(function () {
        $('.modal').modal('hide');
       
         ErrorMessage("Silmek istediğinizden emin misiniz?","Sildiğiniz işlem tekrar geri getirilemeyebilir!")
    });


    $(".btnSave").click(function () {
        $('.modal').modal('hide');


		SuccessMessage("Kaydetme İşlemi","İşlem başarıyla gerçekleştirildi.")
        
    });
    
})

function ErrorMessage(title,message)
{
	swal({
           title: title,
            text: message,
            type: "error",
            showCancelButton: true,
            cancelButtonClass: 'btn-white btn-md waves-effect',
            cancelButtonText: 'İptal',
            confirmButtonClass: 'btn-danger btn-md waves-effect waves-light',
            confirmButtonText: 'Sil',
            html: true
        });
}
function SuccessMessage(title,message)
{
 	swal({
            title: title,
            text: message,
            type: "success",
            showCancelButton: false,
            cancelButtonClass: 'btn-white btn-md waves-effect',
            confirmButtonClass: 'btn-success btn-md waves-effect waves-light',
            confirmButtonText: 'Tamam'
        });
}


function FullScreen(event) {
    event.preventDefault();
    $('body').toggleClass('fullscreen-enable');
    if (!document.fullscreenElement && /* alternative standard method */ !document.mozFullScreenElement && !document.webkitFullscreenElement) {  // current working methods
        if (document.documentElement.requestFullscreen) {
            document.documentElement.requestFullscreen();
        } else if (document.documentElement.mozRequestFullScreen) {
            document.documentElement.mozRequestFullScreen();
        } else if (document.documentElement.webkitRequestFullscreen) {
            document.documentElement.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
        }
    } else {
        if (document.cancelFullScreen) {
            document.cancelFullScreen();
        } else if (document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
        } else if (document.webkitCancelFullScreen) {
            document.webkitCancelFullScreen();
        }
    }

    document.addEventListener('fullscreenchange', exitHandler);
    document.addEventListener("webkitfullscreenchange", exitHandler);
    document.addEventListener("mozfullscreenchange", exitHandler);
    function exitHandler() {
        if (!document.webkitIsFullScreen && !document.mozFullScreen && !document.msFullscreenElement) {
            console.log('pressed');
            $('body').removeClass('fullscreen-enable');
        }
    }
}
