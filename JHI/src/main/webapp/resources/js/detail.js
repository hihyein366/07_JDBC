// 메인화면으로
const goList = document.querySelector("#goList");

goList.addEventListener("click", () => {
    location.href="/";
});

// 수정
const updateBtn = document.querySelector("#updateBtn");
updateBtn.addEventListener("click", e => {
    const bookNo = e.target.dataset.bookNo;
    location.href = `/book/update?bookNo=${bookNo}`;
});

// 삭제
const deleteBtn = document.querySelector("#deleteBtn");
deleteBtn.addEventListener("click", e => {
    if(confirm("삭제 하시겠습니까?")){
        location.href = `/book/delete?bookNo=${e.target.dataset.bookNo}`;
    }
});

