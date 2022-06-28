package ga.continent.store.listener;

import org.hibernate.envers.RevisionListener;

public class UserRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object o) {
//        RevisionEntity revisionEntity = (RevisionEntity) o;
//        OAuth2User user = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        revisionEntity.setUserId(user.getName());
    }
}
