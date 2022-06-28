package ga.continent.service.interfaces;

import ga.continent.api.dto.MetaDto;
import ga.continent.store.entity.MessageEntity;

import java.io.IOException;

public interface MetaContentService {

    void fillMeta(MessageEntity message);

    MetaDto getMeta(String url) throws IOException;

}
