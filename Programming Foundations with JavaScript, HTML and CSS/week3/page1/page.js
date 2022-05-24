function dolime(){
    var cnvs = document.getElementById("cnvs1");
    cnvs.style.backgroundColor = 'lime';
}

function doyellow(){
    var cnvs = document.getElementById("cnvs1");
    cnvs.style.backgroundColor = 'white';
    var cntx = cnvs.getContext("2d");
    cntx.fillStyle = "yellow";
    cntx.fillRect(10, 10, 40, 40);
    cntx.fillRect(60, 10, 40, 40);
    cntx.fillStyle = "black";
    cntx.font = "30px Arial";
    cntx.fillText("Hello Eyvaz", 10, 80);
}

function docolor(){
    var cnvs = document.getElementById("cnvs1");
    var color_input = document.getElementById("clr");
    cnvs.style.backgroundColor = color_input.value;
}

function doscale(){
    var cnvs = document.getElementById("cnvs1");
    var slider_input = document.getElementById("sldr");
    var size = slider_input.value;
    var cntx = cnvs.getContext("2d");
    cntx.clearRect(0, 0, cnvs.width, cnvs.height);
    cntx.fillStyle = "yellow";
    cntx.fillRect(10, 10, size, size);
    cntx.fillRect(20+size, 10, size, size);
    cntx.fillStyle = "black";
    cntx.font = "30px Arial";
    cntx.fillText("Hello Eyvaz", 10, 80);
}