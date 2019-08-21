var onlineShopApp = angular.module("onlineShopApp", ["ngRoute"]);

onlineShopApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html'
		})
		.when('/listProducts', {
			templateUrl : '/app/html/listaArtikala.html',
			controller: 'homeCtrl'
		})
		.when('/kategorija/:id', {
			templateUrl : '/app/html/listaPoKategoriji.html'
		})
		.when('/register', {
			templateUrl : '/app/html/register.html'
		})
		.when('/korpa', {
			templateUrl : '/app/html/artikliUKorpi.html',
			controller: 'homeCtrl',
            resolve: {
                factory: checkRoutingKupac
            }
		})
		.when('/omiljeni', {
			templateUrl : '/app/html/omiljeniArtikli.html',
			controller: 'homeCtrl',
            resolve: {
                factory: checkRoutingKupac
            }
		})
		.when('/ranijeKupljeni', {
			templateUrl : '/app/html/ranijeKupljeni.html',
			controller: 'homeCtrl',
            resolve: {
                factory: checkRoutingKupac
            }
		})
		.when('/login', {
			templateUrl : '/app/html/login.html'
		})
		.when('/pogledajProfil', {
			templateUrl : '/app/html/profilKupac.html',
            resolve: {
                factory: checkRoutingKupac
            }
		})
		.when('/listaPorudzbina', {
			templateUrl : '/app/html/listaKorpi.html',
            resolve: {
                factory: checkRoutingDostavljac
            }
		})
		.when('/listaPreuzetih', {
			templateUrl : '/app/html/listaPreuzetihPorudzbina.html',
            resolve: {
                factory: checkRoutingDostavljac
            }
		})
		.when('/izmenaArtikla/:id', {
			templateUrl : '/app/html/izmenaArtikla.html',
            resolve: {
                factory: checkRoutingAdmin
            }
		})
		.when('/dodajArtikal', {
			templateUrl : '/app/html/dodajArtikal.html',
            resolve: {
                factory: checkRoutingAdmin
            }
		})
		.when('/korisnici', {
			templateUrl : '/app/html/listaKorisnika.html',
            resolve: {
                factory: checkRoutingAdmin
            }
		})
		.when('/dodajDostavljaca', {
			templateUrl : '/app/html/dodajDostavljaca.html',
            resolve: {
                factory: checkRoutingAdmin
            }
		})
		.when('/izmenaDostavljaca/:id', {
			templateUrl : '/app/html/izmenaDostavljaca.html',
            resolve: {
                factory: checkRoutingAdmin
            }
		})
		.when('/kreirajIzvestaj', {
			templateUrl : '/app/html/kreirajIzvestaj.html',
            resolve: {
                factory: checkRoutingAdmin
            }
		})
		.when('/accessDenied', {
			templateUrl : '/app/html/accessDenied.html',
		})
		.otherwise({
			redirectTo: '/'
		});
}]);

// protecting frontend routes

// only kupac is allowed
var checkRoutingKupac = function ($rootScope, $window) {
    if ($rootScope.loggedUser === 'KUPAC') {
        return true;
    } else {
    	$window.location.href = '/#!/accessDenied';
    }
};

// only dostavljac is allowed
var checkRoutingDostavljac = function ($rootScope, $window) {
    if ($rootScope.loggedUser === 'DOSTAVLJAC') {
        return true;
    } else {
    	$window.location.href = '/#!/accessDenied';
    }
};

//only admin is allowed
var checkRoutingAdmin = function ($rootScope, $window) {
    if ($rootScope.loggedUser === 'ADMIN') {
        return true;
    } else {
    	$window.location.href = '/#!/accessDenied';
    }
};

onlineShopApp.controller("homeCtrl", function($scope, $http, $routeParams, $location, $window, $rootScope, $timeout){
	
	var kategorijelUrl = "/api/kategorije";
	var artikliUrl = "/api/artikli";
	
	$scope.kategorije = [];
	$scope.artikli = [];
	
	$scope.searchParams = {};
	$scope.searchParams.naziv = "";
	$scope.searchParams.opis = "";
	$scope.searchParams.cenaOd = "";
	$scope.searchParams.cenaDo = "";
	
	var getArtikli = function() {
		
		var config = {params: {}};
		
		if($scope.searchParams.naziv != ""){
			config.params.naziv = $scope.searchParams.naziv; // naziv u konfiguracionom objektu nmora biti isti kao na backendu!
		}
		if($scope.searchParams.opis != ""){
			config.params.opis = $scope.searchParams.opis;
		}
		if($scope.searchParams.cenaOd != ""){
			config.params.cenaOd = $scope.searchParams.cenaOd;
		}
		if($scope.searchParams.cenaDo != ""){
			config.params.cenaDo = $scope.searchParams.cenaDo;
		}
		
		$http.get(artikliUrl, config).then(
			function success(res) {
				$scope.artikli = res.data;
			},
			function error() {
				alert("Neuspešno dobavljanje artikala!");
			}
		);
	}
	
	getArtikli();
	
	$scope.search = function(){
		getArtikli();
	}

	// get categories
	var getKategorije = function(){
		$http.get(kategorijelUrl).then(
			function success(res){
				$scope.kategorije = res.data;
			},
			function error(){
				alert("Neuspešno dobavljanje kategorija!");
			}
		);
	}
	
	getKategorije();
	
	// for adding class active to element in navbar
	// url matching logic
	$scope.path = $location.path();
	$scope.isActive = function (viewLocation) {
		return viewLocation === $location.path();
	};
	
	$scope.getRastuce = function() {
		$http.get(artikliUrl + '/rastuce').then(
			function success(res) {
				$scope.artikli = res.data;
			},
			function error() {
				alert("Neuspešno dobavljanje artikala!");
			}
		);
	}
	
	$scope.getOpadajuce = function() {
		$http.get(artikliUrl + '/opadajuce').then(
			function success(res) {
				$scope.artikli = res.data;
			},
			function error() {
				alert("Neuspešno dobavljanje artikala!");
			}
		);
	}
	
	$scope.artikliNaPopustu = function() {
		$http.get(artikliUrl + '/popust').then(
			function success(res) {
				$scope.artikli = res.data;
			},
			function error() {
				alert("Neuspešno dobavljanje artikala!");
			}
		);
	}
	
	// for logging
	$scope.korisnik = {};
	$scope.ulogovan = false;
	var korisnik = function() {
		$http.get('/api/korisnik').then(
				function success(res) {
					$scope.korisnik = res.data;
					if($scope.korisnik != "") {
						$rootScope.loggedUser = $scope.korisnik.uloga;
						$scope.ulogovan = true;
						if($scope.korisnik.uloga === "KUPAC") {
							uKorpi();
							omiljeni();
							ranijeKupljeni();
						}
						if($scope.korisnik.uloga === "DOSTAVLJAC") {
							getKorpe();
							getKorpeDobavljac();
						}
						if($scope.korisnik.uloga === "ADMIN") {
							getKorisnici();
						}
					}
				},
				function error() {
					alert("Neuspešno dobavljanje korisnika!");
				}
			);
	}
	
	korisnik();

	// artikli u korpi kod kupca, pozv metode se vrši ako je ulogovani korisnik kupac
	$scope.artikliUKorpi = [];
	var uKorpi = function() {
		$http.get('/api/kupac/' + $scope.korisnik.id + '/korpa').then(
			function success(res) {
				$scope.artikliUKorpi = res.data;
				$scope.ukupnaCena = 0;
				for (let i=0; i < $scope.artikliUKorpi.length; i++) {
					let artikal = $scope.artikliUKorpi[i];
					$scope.ukupnaCena += artikal.cenaSaPopustom;
				}
			},
			function error() {
				alert("Neuspešno dobavljanje artikala!");
			}
		);
	}
	
	// creating shopping cart
	$scope.korpa = {};
	$scope.korpa.kupacId = '';
	$scope.korpa.status = "KUPLJENO";
	$scope.korpa.artikliUKorpiId = [];
	$scope.kreirajKorpu = function(kupacId, artikliUKorpi) {
		$scope.korpa.kupacId = kupacId;
		for(let i=0; i < artikliUKorpi.length; i++) {
			$scope.korpa.artikliUKorpiId.push(artikliUKorpi[i].id);
		}
		$http.post('/api/korpa', $scope.korpa).then(
			function success() {
				alert("Korpa uspešno kreirana!");
				uKorpi();
			},
			function error() {
				alert("Greška!");
			}
		);
	}

	$scope.delete = function(kupacId, artikalId){
		var promise = $http.delete('/api/kupac/' + kupacId + '/' + artikalId);
		promise.then(
			function success(){
				uKorpi();
			},
			function error(){
				alert("Neuspešno brisanje!");
			}
		);
	}
	
	$scope.dodajUKorpu = function(artikal) {
		if($scope.ulogovan) {
			$http.post('/api/kupac/dodajUKorpu', artikal).then(
				function success(res) {
					$window.location.href = 'http://localhost:8080/#!/korpa';
				},
				function error() {
					alert("Greška!");
				}
			);
		} else {
			$window.location.href = 'http://localhost:8080/#!/login';
		}

	}
	
	$scope.omiljeniArtikli = [];
	var omiljeni = function() {
		$http.get('/api/kupac/' + $scope.korisnik.id + '/omiljeni').then(
			function success(res) {
				$scope.omiljeniArtikli = res.data;
			},
			function error() {
				alert("Neuspešno dobavljanje artikala!");
			}
		);
	}
	
	$scope.ranijeKupljeni = [];
	var ranijeKupljeni = function() {
		$http.get('/api/kupac/' + $scope.korisnik.id + '/ranijeKupljeni').then(
			function success(res) {
				$scope.ranijeKupljeni = res.data;
			},
			function error() {
				alert("Neuspešno dobavljanje artikala!");
			}
		);
	}
	
	$scope.dodajUOmiljene = function(artikal) {
		if($scope.ulogovan) {
			$http.post('/api/kupac/dodajUOmiljene', artikal).then(
				function success(res) {
					$window.location.href = 'http://localhost:8080/#!/omiljeni';
				},
				function error() {
					alert("Greška!");
				}
			);
		} else {
			$window.location.href = 'http://localhost:8080/#!/login';
		}

	}
	
	$scope.deleteOmiljeni = function(kupacId, artikalId){
		var promise = $http.delete('/api/kupac/omiljeni/' + kupacId + '/' + artikalId);
		promise.then(
			function success(){
				omiljeni();
			},
			function error(){
				alert("Neuspešno brisanje!");
			}
		);
	}
	
	// dostavljac functions
	
	// carts without dostavljac
	$scope.korpe = [];
	var getKorpe = function() {
		$http.get('/api/dostavljac').then(
			function success(res) {
				$scope.korpe = res.data;
			},
			function error() {
				alert("Greška!");
			}
		);
	}
	
	// carts with dostavljac
	$scope.korpeDobavljac = [];
	$scope.statusi = ['DOSTAVA_U_TOKU', 'DOSTAVLJENO'];
	var getKorpeDobavljac = function() {
		$http.get('/api/dostavljac/preuzete').then(
			function success(res) {
				$scope.korpeDobavljac = res.data;
			},
			function error() {
				alert("Greška!");
			}
		);
	}
	
	$scope.preuzmiPorudzbinu = function(porudzbina) {
		$http.put('/api/dostavljac/' + $scope.korisnik.id, porudzbina).then(
			function success() {
				alert('Uspešno preuzeta porudžbina!');
				getKorpe();
			},
			function error() {
				alert("Greška!");
			}
		);
	}
	
	$scope.dostaviPorudzbinu = function(porudzbina) {
		$http.put('/api/dostavljac/dostavljeno', porudzbina).then(
			function success() {
				alert('Uspešno dostavljena porudžbina!');
				getKorpeDobavljac();
			},
			function error() {
				alert("Greška!");
			}
		);
	}
	
	// admin functions
	$scope.obrisi = function(id) {
		$http.delete('/api/admin/' + id).then(
			function success() {
				getArtikli();
			},
			function error() {
				alert("Greška!");
			}
		);
	}
	
	$scope.goToEdit = function(id){
		$location.path("/izmenaArtikla/" + id);
	}
	$scope.goToDodaj = function(){
		$location.path("/dodajArtikal");
	}
	
	$scope.uloge = ['ADMIN', 'DOSTAVLJAC', 'KUPAC'];
	$scope.korisnici = [];
	var getKorisnici = function() {
		$http.get('/api/admin/korisnici').then(
			function success(res) {
				$scope.korisnici = res.data;
			},
			function error() {
				alert("Greška!");
			}
		);
	}
	
	$scope.snimiPromenu = function(korisnik) {
		$http.put('/api/admin/snimanjeUloge', korisnik).then(
			function success(){
				alert('Izmena uspešno sačuvana!');
				getKorisnici();
			},
			function error() {
				alert("Greška!");
			}
		);
	}
	
	$scope.goToDodajDostavljaca = function() {
		$location.path("/dodajDostavljaca");
	}
	
	$scope.goToEditDostavljac = function(id) {
		$location.path("/izmenaDostavljaca/" + id);
	}
	
	$scope.obrisiDostavljaca = function(id) {
		$http.delete('/api/admin/dostavljac/' + id).then(
			function success() {
				getKorisnici();
			},
			function error() {
				alert("Greška!");
			}
		);
	}
	
});

onlineShopApp.controller('kategorija', function($scope, $http, $routeParams){
	
	$scope.artikliPoKategoriji = [];
	$scope.nazivKategorije = "";
	var id = $routeParams.id;
	var getArtikliByKategorija = function() {
		$http.get('/api/kategorije/' + id + '/artikli').then(
			function success(res) {
				$scope.artikliPoKategoriji = res.data;
				if($scope.artikliPoKategoriji.length != 0) {
					$scope.nazivKategorije = $scope.artikliPoKategoriji[0].nazivKategorije;
				}
			},
			function error() {
				alert("Neuspešno dobavljanje artikala!");
			}
		);
	}
	
	getArtikliByKategorija();

});

onlineShopApp.controller('registerCtrl', function($scope, $http){
	
	$scope.kupac = {};
	$scope.kupac.korisnickoIme = "";
	$scope.kupac.lozinka = "";
	$scope.kupac.ime = "";
	$scope.kupac.prezime = "";
	$scope.kupac.telefon = "";
	$scope.kupac.email = "";
	$scope.kupac.adresa = "";
	
	var addKupac = function() {
		$http.post('/api/korisnik/registracija', $scope.kupac).then(
			function success() {
				alert("Kupac registrovan!");
			},
			function error() {
				alert("Registracija neuspešna!");
			}
		);
	}
	
	$scope.korImeValidacija = false;
	$scope.lozinkaValidacija = false;
	$scope.imeValidacija = false;
	$scope.prezimeValidacija = false;
	$scope.telefonValidacija = false;
	$scope.emailValidacija = false;
	$scope.adresaValidacija = false;
	
	$scope.validateFunction = function() {
		if($scope.kupac.korisnickoIme.length < 3) {
			$scope.korImeValidacija = true;
		};
		if($scope.kupac.lozinka.length < 3) {
			$scope.lozinkaValidacija = true;
		};
		if($scope.kupac.ime == "") {
			$scope.imeValidacija = true;
		};
		if($scope.kupac.prezime == "") {
			$scope.prezimeValidacija = true;
		};
		if($scope.kupac.telefon == "") {
			$scope.telefonValidacija = true;
		};
		if($scope.kupac.email == "") {
			$scope.emailValidacija = true;
		};
		if($scope.kupac.adresa == "") {
			$scope.adresaValidacija = true;
		};
		
		if (!$scope.korImeValidacija && !$scope.lozinkaValidacija && !$scope.imeValidacija && !$scope.prezimeValidacija &&
				!$scope.telefonValidacija && !$scope.emailValidacija && !$scope.adresaValidacija) {
			
			addKupac();
		}
	}
	
});

onlineShopApp.controller('izmenaArtikla', function($scope, $http, $routeParams, $location){
	
	$scope.kategorije = [];
	
	var kategorijeUrl = '/api/kategorije';
	var artikalUrl = '/api/artikli/' + $routeParams.id;
	
	$scope.artikal = {};
	$scope.artikal.naziv = "";
	$scope.artikal.opis = "";
	$scope.artikal.cena = "";
	$scope.artikal.imageUrl = "";
	$scope.artikal.popust = "";
	$scope.artikal.kategorijaId = "";
	
	var getKategorije = function() {
		$http.get(kategorijeUrl).then(
			function success(res) {
				$scope.kategorije = res.data;
				getArtikal();
			},
			function error() {
				alert("Greška!");
			}
		);
	}
	
	var getArtikal = function() {
		$http.get(artikalUrl).then(
			function success(res) {
				$scope.artikal = res.data;
			},
			function error() {
				alert("Greška!");
			}
		);
	}
	
	getKategorije();
	
	$scope.izmeni = function(){
		$http.put('/api/admin/' + $routeParams.id, $scope.artikal).then(
			function success(){
				$location.path("/listProducts");
			},
			function error(){
				alert("Greška!");
			}
		);
	}

});

onlineShopApp.controller('dodavanjeArtikla', function($scope, $http, $location){
	
	var kategorijeUrl = '/api/kategorije';
	$scope.kategorije = [];
	
	$scope.artikal = {};
	$scope.artikal.naziv = "";
	$scope.artikal.opis = "";
	$scope.artikal.cena = "";
	$scope.artikal.imageUrl = "";
	$scope.artikal.popust = "";
	$scope.artikal.kategorijaId = "";
	
	var getKategorije = function() {
		$http.get(kategorijeUrl).then(
			function success(res) {
				$scope.kategorije = res.data;
			},
			function error() {
				alert("Greška!");
			}
		);
	}

	getKategorije();
	
	$scope.dodaj = function(){
		$http.post('/api/admin/dodajArtikal', $scope.artikal).then(
			function success(){
				$location.path("/listProducts");
			},
			function error(){
				alert("Greška!");
			}
		);
	}
});

onlineShopApp.controller('dodavanjeDostavljaca', function($scope, $http, $location){
	
	$scope.newDostavljac = {};
	$scope.newDostavljac.ime = "";
	$scope.newDostavljac.prezime = "";
	$scope.newDostavljac.korisnickoIme = "";
	$scope.newDostavljac.lozinka = "";
	$scope.newDostavljac.email = "";
	$scope.newDostavljac.telefon = "";
	$scope.newDostavljac.adresa = "";
	$scope.newDostavljac.uloga = "DOSTAVLJAC";
	
	$scope.dodaj = function(){
		$http.post('/api/admin/dodajDostavljaca', $scope.newDostavljac).then(
			function success(){
				$location.path("/korisnici");
			},
			function error(){
				alert("Greška!");
			}
		);
	}
});

onlineShopApp.controller('izmenaDostavljaca', function($scope, $http, $routeParams, $location){
	
	var id = $routeParams.id;
	
	$scope.dostavljac = {};
	$scope.dostavljac.ime = "";
	$scope.dostavljac.prezime = "";
	$scope.dostavljac.korisnickoIme = "";
	$scope.dostavljac.lozinka = "";
	$scope.dostavljac.email = "";
	$scope.dostavljac.telefon = "";
	$scope.dostavljac.adresa = "";
	$scope.dostavljac.uloga = "DOSTAVLJAC";
	
	$scope.izmeni = function() {
		$http.put('/api/admin/izmeniDostavljaca/' + id, $scope.dostavljac).then(
			function success() {
				$location.path("/korisnici");
			},
			function error() {
				alert("Greška!");
			}
		);
	}
	
	var getDostavljac = function() {
		$http.get('/api/admin/getDostavljac/' + id).then(
			function success(res) {
				$scope.dostavljac = res.data;
			},
			function error() {
				alert("Greška!");
			}
		);
	}
	
	getDostavljac();
});