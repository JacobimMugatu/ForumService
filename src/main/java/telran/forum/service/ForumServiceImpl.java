package telran.forum.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.forum.dao.ForumRepository;
import telran.forum.domain.Comment;
import telran.forum.domain.Post;
import telran.forum.dto.DatePeriodDto;
import telran.forum.dto.NewCommentDto;
import telran.forum.dto.NewPostDto;
import telran.forum.dto.PostUpdateDto;

@Service
public class ForumServiceImpl implements ForumService {
	
	@Autowired
	ForumRepository forumRepository;

	@Override
	public Post addNewPost(NewPostDto newPost) {
		
		Post post = new Post(newPost.getTitle(), newPost.getContent(), newPost.getAuthor(), newPost.getTags());
		forumRepository.save(post);
		return post;
	}

	@Override
	public Post getPost(String id) {
		
		return forumRepository.findById(id);
	}

	@Override
	public Post removePost(String id) {
		
		
		Post post = getPost(id);
		if(post != null) {
			forumRepository.deleteById(id);
		}
		return post;
	}

	@Override
	public Post updatePost(PostUpdateDto updatePost) {
		Post post = getPost(updatePost.getId());
		if(post != null) {
			post.setContent(updatePost.getContent());
			forumRepository.save(post);
		}
		return post;
	}

	@Override
	public boolean addLike(String id) {
		Post post = getPost(id);
		if(post != null) {
			post.addLike();
			forumRepository.save(post);
			return true;
		}
		return false;
	}

	@Override
	public Post addComment(String id, NewCommentDto newComment) {
		Comment comment = new Comment(newComment.getUser(), newComment.getMessage());
		Post post = getPost(id);
		if(post != null) {
			post.addComment(comment);
			forumRepository.save(post);
		}
		return post;
	}

	@Override
	public Iterable<Post> findByTags(List<String> tags) {
		
		return forumRepository.findByTagsIn(tags);
	}

	@Override
	public Iterable<Post> findByAuthor(String author) {
		
		return forumRepository.findByAuthor(author);
	}

	@Override
	public Iterable<Post> findByDate(DatePeriodDto period) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			LocalDate a = LocalDate.parse(period.getFrom(), dtf);
			LocalDate b = LocalDate.parse(period.getTo(), dtf).plus(1, ChronoUnit.DAYS);
			return forumRepository.findByDateCreatedBetween(a, b);
		}catch (IllegalArgumentException e) {
			
		}
		return null;
	}

	@Override
	public boolean addCommentLike(String id, NewCommentDto newComment) {
		Post post = getPost(id);
		if(post != null) {
			if(post.getComments().isEmpty()) {
				return false;
			}
			Comment c = post.getComments()
					.stream()
					.filter(x -> x.getUser().equals(newComment.getUser()))
					.filter(x -> x.getMessage().equals(newComment.getMessage()))
					.findFirst()
					.orElse(null);
			if(c != null) {
				c.addLike();
				forumRepository.save(post);
				return true;
			}
					
		}
		return false;
	}

	
}
