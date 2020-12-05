package es.urjc.cloudapps.library.data;

import es.urjc.cloudapps.library.domain.Comment;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InMemoryCommentRepository {

    private Map<String, Comment> comments;

    public InMemoryCommentRepository() {
        this.comments = Collections.synchronizedMap(new HashMap<>());
    }

    public void save(Comment comment) {

        comments.put(comment.getId(), comment);
    }

    public void remove(Comment comment) {
        comments.remove(comment);
    }

}
