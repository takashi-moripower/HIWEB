/**
 *
 */

$(function(){
	$('.pref-selecter').hide();
	$('.pref-result').on('click', function() {
		$('.pref-selecter').show();
		$('.pref-result').hide();
	});

	$('input[name="pref"]').on('change', onChangePref);
	$('input[name="pref"]').trigger('change');

	$("input[name^='city']").on('change',checkCities);
	checkCities();

	$("input[name='all-city']").on('change',onAllCity);
});


function onChangePref(){
	pref = $('input[name="pref"]:checked').map(function() {
		return $(this).val();
	}).get();

	if( pref.length == 1 ){
		prefCd = pref[0];
		activateCity( $("div.city-selecter[pref_cd='"+prefCd+"']") );
		inactivateCity( $("div.city-selecter[pref_cd!='"+prefCd+"']") );
	}else{
		inactivateCity( $("div.city-selecter") );
	}
}

function activateCity( target ){
	target.show();
	target.find("input[name='city']").removeAttr('disabled');
}

function inactivateCity( target ){
	target.hide();
	target.find("input[name='city']").attr('disabled','disabled');
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