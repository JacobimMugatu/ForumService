package telran.forum.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.forum.domain.Post;

<<<<<<< HEAD

public interface ForumRepository extends MongoRepository<Post, Integer> {
=======
public interface ForumRepository extends MongoRepository<Post, String> {
>>>>>>> branch 'master' of https://github.com/TelRanJava23/ForumService.git
	Iterable<Post> findByTagsIn(List<String> tags);
	
	Iterable<Post> findByAuthor(String author);
	
	Iterable<Post> findByDateCreatedBetween(LocalDate from, LocalDate to);
	
	Stream<Post> findAllBy();

	Post findById(String id);

	
}
