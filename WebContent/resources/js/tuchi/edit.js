/**
 *
 */

$(function(){

	$("select[name='prefCd']").on('change',onPrefChange);
	setPref();

	$("input[name^='city']").on('change',checkCities);
	checkCities();

	$("input[name='all-city']").on('change',onAllCity);
});

function onPrefChange(e){
	setPref();
	$("input[name^='city']").prop("checked",false);
	$("input[type='checkbox'][name='all-city']").prop("checked",true);
}

function setPref(){
	perfCd = $("select[name='prefCd']").val();

	$("tr[pref_cd]").hide();

	if( perfCd != "" ){
		$("tr[pref_cd=" + perfCd + "]").show();
	}
}

function onAllCity(e){
	c =  $(e.target).prop("checked") ;

	if( c ){
		$("input[name^='city']").prop("checked",false);
	}else{
		checkCities();
	}

}

function checkCities(){
	if( isCitySelected() ){
		$("input[type='checkbox'][name='all-city']").prop("checked",false);
	}else{
		$("input[type='checkbox'][name='all-city']").prop("checked",true);
	}
}

function isCitySelected(){
	result = 0;
	$("input[type='checkbox'][name^='city']").each(function(index,element){
		if( $(this).prop("checked") ){
			result = 1;
			return false;
		}
	});

	return result;
}