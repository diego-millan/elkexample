package com.spring.elk.demo;

import com.spring.elk.demo.model.Movie;
import com.spring.elk.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
				"Universal Studios", new GregorianCalendar(1993, Calendar.JUNE, 13).getTime(), 3);
		Movie movie2 = new Movie("101", "Jurassic Park - Lost Word", "Steven Spielberg",
				"Universal Studios", new GregorianCalendar(1997, Calendar.DECEMBER, 05).getTime(), 0);
		Movie movie3 = new Movie("102", "Jurassic Park III", "Joe Johnston",
				"Universal Studios", new GregorianCalendar(2001, Calendar.NOVEMBER, 07).getTime(), 0);
		Movie movie4 = new Movie("103", "Jurassic World", "Collin Trevorrow",
				"Universal Studios", new GregorianCalendar(2015, Calendar.JULY, 12).getTime(), 0);
		Movie movie5 = new Movie("104", "Back To The Future", "Robert Zemeckis",
				"Universal Studios", new GregorianCalendar(1985, Calendar.JUNE, 02).getTime(), 1);
		Movie movie6 = new Movie("105", "Forrest Gump", "Robert Zemeckis",
				"Paramount Pictures", new GregorianCalendar(1999, Calendar.APRIL, 04).getTime(), 6);
		Movie movie7 = new Movie("106", "Fight Club", "David Fincher",
				"Fox Pictures", new GregorianCalendar(1999, Calendar.DECEMBER, 02).getTime(), 0);
		Movie movie8 = new Movie("107", "The Shawshank Redemption", "Frank Darabont",
				"Castle Rock", new GregorianCalendar(1994, Calendar.OCTOBER, 14).getTime(), 0);
		Movie movie9 = new Movie("108", "Schindler's List", "Steven Spielberg",
				"Universal Studios", new GregorianCalendar(1994, Calendar.FEBRUARY, 04).getTime(), 7);
		Movie movie10 = new Movie("109", "The Lion King", "Roger Allers",
				"Disney", new GregorianCalendar(1994, Calendar.JUNE, 24).getTime(), 2);
		Movie movie11 = new Movie("110", "Pulp Fiction", "Quentin Tarantino",
				"Miramax", new GregorianCalendar(1994, Calendar.OCTOBER, 14).getTime(), 1);
		Movie movie12 = new Movie("111", "Star Wars: Episode V - The Empire Strikes Back","Irvin Kershner",
				"Lucas Film",  new GregorianCalendar(1980, Calendar.MAY, 20).getTime(), 1);
		Movie movie13 = new Movie("112", "Star Wars: Episode IV - A New Hope", "George Lucas",
				"Lucas Film", new GregorianCalendar(1977, Calendar.MAY, 13).getTime(), 6);
		Movie movie14 = new Movie("113", "Star Wars: Episode VI - Return of the Jedi", "Richard Marquand",
				"Lucas Film", new GregorianCalendar(1983, Calendar.MAY, 25).getTime(), 1);

		List<Movie> movieList = Arrays.asList(movie1, movie2, movie3, movie4, movie5, movie6, movie7, movie8,
				movie9, movie10, movie11, movie12, movie13, movie14);

		movieService.saveAll(movieList);
	}
}
