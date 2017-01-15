/**
 *
 */

$(function(){

	$("select[name='prefCd']").on('change',onPrefChange);

	$("select[name='prefCd']").trigger('change');

});

function onPrefChange(e){

	perfCd = $("select[name='prefCd']").val();

	$("tr[pref_cd]").hide();

	if( perfCd != "" ){
		$("tr[pref_cd=" + perfCd + "]").show();
	}
	console.log("onPrefChange");
	console.log(perfCd);
}