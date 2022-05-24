function docall(id) {
    alert("You are pressing JS button --> " + id)
}
function changecolor() {
    var dd1 = document.getElementById("div1");
    var dd2 = document.getElementById("div2");
    dd1.className = "orange";
    dd2.className = "fuchsia";
}

function changetext() {
    var dd1 = document.getElementById("div1");
    var dd2 = document.getElementById("div2");
    dd1.innerText = "Heeey, you have just changed DIV1 content"
    dd2.innerText = "Heeey, you have just changed DIV2 content"
}