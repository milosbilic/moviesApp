var moviesApp = angular.module('moviesApp', ['ngRoute']);

moviesApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider
    .when('/movies', {
        templateUrl : '/static/app/html/partial/movies.html'
    })
    .when('/addMovie/:id', {
        templateUrl : '/static/app/html/partial/addMovie.html'
    })
    .otherwise({
        redirectTo: '/'
    });
}]);

moviesApp.controller('moviesController', function($scope, $http, $location){
	var paramsConfig = {};
	$scope.currentPage = 0;
	$scope.totalPages = 0;

	$scope.configureParameters = function(){
		paramsConfig = { params:{} };
		if($scope.currentPage)
			paramsConfig.params.page = $scope.currentPage;

		$scope.load();
	}

	$scope.filterParameters = function(){
		paramsConfig = { params:{} };
		if($scope.currentPage)
			paramsConfig.params.page = $scope.currentPage;
		if($scope.filterTitle)
			paramsConfig.params.filterTitle = $scope.filterTitle;
		if($scope.filterYear)
			paramsConfig.params.filterYear = Number($scope.filterYear);
		if($scope.filterRating)
			paramsConfig.params.filterRating = Number($scope.filterRating);
		if($scope.filterGenre)
			paramsConfig.params.filterGenre = Number($scope.filterGenre);
		
		$scope.filter();
	}

	$scope.changePage = function(i){
		if(($scope.currentPage > 0 && i < 0) || (i > 0 && $scope.currentPage < $scope.totalPages)){
			$scope.currentPage += i;
			$scope.configureParameters();
		}
	}

	$scope.load = function(){
		$http.get('/api/movies', paramsConfig).then(function(response){
			console.log('(Load Movies) Success: ', response.status, response.statusText);
			$scope.movies = response.data;
			$scope.totalPages = response.headers('totalPages');
		}, function(response){
			console.log('(Load Movies) Error: ', response.status, response.statusText);
		});
	}

	$scope.filter = function(){
		$http.get('/api/movies/filterMovies', paramsConfig).then(function(response){
			console.log('(Filter Movies) Success: ', response.status, response.statusText);
			$scope.movies = response.data;
		}, function(response){
			console.log('(Filter Movies) Error: ', response.status, response.statusText);
		});
	}

	$scope.load();

	$scope.save = function(){
		$http.post('/api/movies/', $scope.movie).then(function(response){
			console.log('(Add Movie) Success:', response.status, response.statusText);
			$scope.currentPage = 0;
			$scope.configureParameters();
			resetInputValues();
		}, function(response){
			console.log('(Add Movie) Error:', response.status, response.statusText);
		});
	}

	$scope.delete = function(id){
		$http.delete('/api/movies/' + id).then(function(response){
			console.log('(Delete Movie) Success:', response.status, response.statusText);
			$scope.load();
		}, function(response){
			console.log('(Delete Movie) Error:', response.status, response.statusText);
		});
	}

	$scope.printData = function(){
		console.log($scope.filterTitle + ' ' + $scope.filterYear + ' ' + $scope.filterRating + ' ' + $scope.filterGenre);
	}

	$scope.goToAddMovieHTML = function(id){
		$location.path('/addMovie/' + id);
	}

	$scope.setMovieValues = function(movie){
		$scope.movie = angular.copy(movie);
	}

	var resetInputValues = function(){
		$scope.movie = {};
	}

	var loadGenres = function(){
		$http.get('/api/genres').then(function(response){
			console.log('(Load Genres) Success:', response.status, response.statusText);
			$scope.genres = response.data;
		}, function(response){
			console.log('(Load Genres) Error:', response.status, response.statusText);
		});
	}

	loadGenres();
});

moviesApp.controller('addMovieController', function($scope, $http, $location, $routeParams){
	$scope.loadMovie = function(){
		$http.get('/api/movies/' + $routeParams.id).then(function(response){
			console.log('(Get Movie) Success', response.status, response.statusText);
			$scope.movie = response.data;
		}, function(response){
			console.log('(Get Movie) Error', response.status, response.statusText);
		});
	}

	$scope.loadMovie();

	$scope.save = function(){
		$http.post('/api/movies/', $scope.movie).then(function(response){
			console.log('(Add Movie) Success:', response.status, response.statusText);
			$location.url('/movies');
		}, function(response){
			console.log('(Add Movie) Error:', response.status, response.statusText);
		});
	}

	var loadGenres = function(){
		$http.get('/api/genres').then(function(response){
			console.log('(Load Genres) Success:', response.status, response.statusText);
			$scope.genres = response.data;
		}, function(response){
			console.log('(Load Genres) Error:', response.status, response.statusText);
		});
	}

	loadGenres();
});