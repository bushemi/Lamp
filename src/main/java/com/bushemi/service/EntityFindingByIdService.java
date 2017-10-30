package com.bushemi.service;

import com.bushemi.model.entity.*;

import java.util.Collection;

/**
 * Created by igor on 24.10.17.
 *
 * @Version 1.0
 */
public interface EntityFindingByIdService {
    PersonDto getPersonDtoById(long id);

    Collection<PlaceDto> getPlacesDtoByPersonId(long id);
    Collection<PostDto> getPostsDtoByOwnerId(long id);
    Collection<HobbyDto> getHobbiesDtoByPersonId(long id);
    Collection<FriendshipDto> getFriendshipsDtoByPersonId(long id);
    Collection<MessageDto> getMessagesDtoByPersonId(long id);

    PostDto getPostsDtoById(long id);
}
