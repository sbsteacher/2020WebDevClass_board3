
//글 제목 클릭
function clkArticle(typ, i_board) {		
	var url = `/bDetail?typ=${typ}&i_board=${i_board}`;	
	location.href = url; //주소값 이동
}

function chk() {
	if(chkEmptyEle(frm.title, '제목') 
	|| chkEmptyEle(frm.ctnt, '내용')){
		return false;
	}
}