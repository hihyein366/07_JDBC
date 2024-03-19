const header = document.querySelector("#header");
header.addEventListener("click", () => {
    location.href="/";
});

function goBack() {
    event.preventDefault();
    window.history.back();
}
