function redirect(url)
{
	showLoading();
	window.location.href = url;
	}

function showLoading()
{
	showLoader();
	showOverlay();
	}

function showLoader(){
	document.getElementById("loader").style.visibility="visible"
}

function showOverlay()
{
	document.getElementById("overlay").style.display="block"
}