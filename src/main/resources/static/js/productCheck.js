 // 메인 카테고리 변경 시 실행되는 함수
   function updateSubCategories() {
       var selectedMainCategory = document.getElementById('mainCategory').value;
       var subCategoryDropdown = document.getElementById('subCategory');

       // 서브 카테고리 옵션 초기화
       subCategoryDropdown.innerHTML = '';

       // 선택한 메인 카테고리에 따라 적절한 서브 카테고리 옵션 추가
       if (selectedMainCategory === '인형') {
           subCategoryDropdown.innerHTML += '<option value="">서브 카테고리를 선택해주세요</option>';
           subCategoryDropdown.innerHTML += '<option value="사람인형">사람인형</option>';
           subCategoryDropdown.innerHTML += '<option value="동물인형">동물인형</option>';
           subCategoryDropdown.innerHTML += '<option value="캐릭터인형">캐릭터인형</option>';
       } else if (selectedMainCategory === '완구') {
            subCategoryDropdown.innerHTML += '<option value="">서브 카테고리를 선택해주세요</option>';
           subCategoryDropdown.innerHTML += '<option value="레고">레고</option>';
           subCategoryDropdown.innerHTML += '<option value="촉각놀이">촉각놀이</option>';
           subCategoryDropdown.innerHTML += '<option value="실외장난감">실외장난감</option>';
       } else if (selectedMainCategory === '도서') {
            subCategoryDropdown.innerHTML += '<option value="">서브 카테고리를 선택해주세요</option>';
           subCategoryDropdown.innerHTML += '<option value="동화">동화</option>';
           subCategoryDropdown.innerHTML += '<option value="위인전">위인전</option>';
           subCategoryDropdown.innerHTML += '<option value="영어도서">영어도서</option>';
       }
   }


function checkProductStock() {
        var productStockInput = document.getElementById('productStock');
        var errorDivStock = document.getElementById('errorDivStock');
        var stockStatus = document.getElementById('stockStatus');

        if (productStockInput.value < 0) {
            errorDivStock.innerText = '상품 수량은 0보다 큰 정수값으로 입력하세요.';
            stockStatus.innerText = '';
        } else if (productStockInput.value == 0) {
            errorDivStock.innerText = '';
            stockStatus.innerText = '품절상태로 등록됩니다';
        } else {
            errorDivStock.innerText = '';
            stockStatus.innerText = '';
        }
    }

    function checkProductPrice() {
        var productPriceInput = document.getElementById('productPrice');
        var errorDivPrice = document.getElementById('errorDivPrice');

        if (productPriceInput.value <= 0) {
            errorDivPrice.innerText = '상품 가격은 0보다 큰 정수값으로 입력하세요.';
        } else {
            errorDivPrice.innerText = '';
        }
    }

    function checkProductName() {
        var productNameInput = document.getElementById('productName');
        var errorDivName = document.getElementById('errorDivName');

        if (productNameInput.value.trim() === '') {
            errorDivName.innerText = '상품 이름을 입력하세요.';
        } else {
            errorDivName.innerText = '';
        }
    }

    function checkCategory() {
        var mainCategoryInput = document.getElementById('mainCategory');
        var subCategoryInput = document.getElementById('subCategory');
        var errorDivMainCategory = document.getElementById('errorDivMainCategory');
        var errorDivSubCategory = document.getElementById('errorDivSubCategory');

        if (mainCategoryInput.value.trim() === '') {
            errorDivMainCategory.innerText = '메인 카테고리를 선택하세요.';
        } else {
            errorDivMainCategory.innerText = '';
        }

        if (subCategoryInput.value.trim() === '') {
            errorDivSubCategory.innerText = '서브 카테고리를 선택하세요.';
        } else {
            errorDivSubCategory.innerText = '';
        }
    }

    function checkContentImgFile() {
        var contentImgfileInput = document.getElementById('contentImgFile');
        var errorDivContentImgfileInput = document.getElementById('errorDivContentImgfileInput');

        if (contentImgfileInput.files.length > 0) {
            errorDivContentImgfileInput.innerText = '';
        } else {
            errorDivContentImgfileInput.innerText = '이미지 파일을 등록해 주세요';
        }
    }

    function checkImgFile() {
        var imgfileInput = document.getElementById('imgFile');
        var errorDivImgfileInput = document.getElementById('errorDivImgfileInput');

        if (imgfileInput.files.length > 0) {
            errorDivImgfileInput.innerText = '';
        } else {
            errorDivImgfileInput.innerText = '이미지 파일을 등록해 주세요';
        }
    }

    function checkFormSubmit(event) {
        checkProductName();
        checkProductStock();
        checkProductPrice();
        checkCategory();
        checkContentImgFile();
        checkImgFile();

        var errorDivName = document.getElementById('errorDivName');
        var errorDivStock = document.getElementById('errorDivStock');
        var errorDivPrice = document.getElementById('errorDivPrice');
        var errorDivMainCategory = document.getElementById('errorDivMainCategory');
        var errorDivSubCategory = document.getElementById('errorDivSubCategory');
        var errorDivContentImgfileInput = document.getElementById('errorDivContentImgfileInput');
        var errorDivImgfileInput = document.getElementById('errorDivImgfileInput');

        if (errorDivName.innerText !== '' || errorDivStock.innerText !== '' || errorDivPrice.innerText !== '' ||
            errorDivMainCategory.innerText !== '' || errorDivSubCategory.innerText !== '' ||
            errorDivContentImgfileInput.innerText !== '' || errorDivImgfileInput.innerText !== '') {
            event.preventDefault();
        }
    }