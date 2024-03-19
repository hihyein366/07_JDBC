// 기록 추가화면으로
const addBook = document.querySelector("#addBook");
addBook.addEventListener("click", () => {
    location.href = "/book/add"; // get
});

const header = document.querySelector("#header");
header.addEventListener("click", () => {
    location.href="/";
});

