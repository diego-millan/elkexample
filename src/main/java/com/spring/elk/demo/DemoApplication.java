package com.spring.elk.demo;

import com.spring.elk.demo.model.Movie;
import com.spring.elk.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	MovieService movieService;

	@Autowired
	private ElasticsearchTemplate esTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		esTemplate.deleteIndex(Movie.class);
		esTemplate.createIndex(Movie.class);
		esTemplate.putMapping(Movie.class);
		esTemplate.refresh(Movie.class);

		Movie movie1 = new Movie("100", "Jurassic Park", "Steven Spielberg",
				"Universal Studios", "13-Jun 1993");
		Movie movie2 = new Movie("101", "Jurassic Park - Lost Word", "Steven Spielberg",
				"Universal Studios", "05-Dec 1997");
		Movie movie3 = new Movie("102", "Jurassic Park III", "Joe Johnston",
				"Universal Studios", "07-Nov 2001");
		Movie movie4 = new Movie("103", "Jurassic World", "Collin Trevorrow",
				"Universal Studios", "12-Jul 2015");
		Movie movie5 = new Movie("104", "Back To The Future", "Robert Zemeckis",
				"Universal Studios", "02-Jun 1985");
		Movie movie6 = new Movie("105", "Forrest Gump", "Robert Zemeckis",
				"Paramount Pictures", "04-Apr 1999");
		Movie movie7 = new Movie("106", "Fight Club", "David Fincher",
				"Fox Pictures", "02-Dec 1999");
		Movie movie8 = new Movie("107", " The Shawshank Redemption ", "Frank Darabont",
				"Castle Rock", "14-Oct 1994");
		Movie movie9 = new Movie("108", "Schindler's List", "Steven Spielberg",
				"Universal Studios", "04-Feb 1994");
		Movie movie10 = new Movie("109", "The Lion King", "Roger Allers",
				"Disney", "24-Jun 1994");
		Movie movie11 = new Movie("110", "Pulp Fiction", "Quentin Tarantino",
				"Miramax", "14-Oct 1994");
		Movie movie12 = new Movie("111", "Star Wars: Episode V - The Empire Strikes Back",
				"Irvin Kershner", "Lucas Film", "20-May 1980");
		Movie movie13 = new Movie("112", "Star Wars: Episode IV - A New Hope", "George Lucas",
				"Lucas Film", "13-May 1977");
		Movie movie14 = new Movie("113", "Star Wars: Episode VI - Return of the Jedi", "Richard Marquand",
				"Lucas Film", "25-May 1983");

		List<Movie> movieList = Arrays.asList(movie1, movie2, movie3, movie4, movie5, movie6, movie7, movie8,
				movie9, movie10, movie11, movie12, movie13, movie14);

		movieService.saveAll(movieList);
	}
}
