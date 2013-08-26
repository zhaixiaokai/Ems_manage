
/**
 * ÓÃÓÚ·­Ò³
 * @return
 */
function page() {
	$("#PageForm").submit();
}
function nextPage() {
	$("#pageNum").val($("#pageNum").val() - 1 + 2);
	page();
}

function prePage() {
	$("#pageNum").val($("#pageNum").val() - 1);
	page();
}
function firstPage(){
	$("#pageNum").val(1);
	page();
}
function lastPage(o){

	$("#pageNum").val(o);
	page();
}