$(document).ready(function(){
	$.validator.setDefaults({
		highlight: function(element){
			$(element)
			.closest('.form-group')
			.addClass('has-error')
		},
		unhighlight: function(element){
			$(element)
			.closest('.form-group')
			.removeClass('has-error')
		}
	});
	
		$('#forma').validate({
			rules: {	
				korIme: {
					required: true,
					minlength: 3
				},
				lozinka: {
					required: true,
					minlength: 5
				},
				ime: "required",
				prezime: "required",
				email: {
					required: true,
					email: true
				}
			},
			messages:{			
				email: {
					required: "Mora biti popunjeno",
					email: "Morate uneti validan email"
				},
				korIme: {
					required: "Molimo Vas unesite korisničko ime",
					minlength: "Minimum 3 karaktera"
				},
				lozinka : {
					required: " Please enter birthdate"
				},
				email: {
					required: ' Please enter email',
					email: ' Please enter valid email'
				},
				weight: {
					required: " Please enter your weight",
					number: " Only numbers allowed"
				},
				height: {
					required: " Please enter your height",
					number: " Only numbers allowed"
				}
			}

	  });
  
});