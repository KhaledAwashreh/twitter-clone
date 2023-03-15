package javalin.domain.service.impl;

import javalin.data.dto.TweetItemDto;
import javalin.domain.repository.TweetItemRepository;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TweetItemServiceImplTest {
    Map<Long, TweetItemDto> mockDatabase = new HashMap<Long, TweetItemDto>(){{
        put((long)22,new TweetItemDto());
        put((long)11,new TweetItemDto());
        put((long)30,new TweetItemDto());
        put((long)10,new TweetItemDto());
        put((long)9,new TweetItemDto());
        put((long)1,new TweetItemDto());
    }};
    TweetItemRepository tweetItemRepository = mock(TweetItemRepository.class);

    TweetItemServiceImpl tweetItemService = new TweetItemServiceImpl(tweetItemRepository);

    @Test
    public void shouldReturnNullWhenItemDoesntExist() {
        long id = (long) 999.00;
        when(tweetItemService.getTweetById(id)).thenReturn(searchInMap(id, mockDatabase));
        assertNull(tweetItemRepository.getTweetById(id));
    }

    @Test
    public void shouldReturnTweetItemWhenItemExists() {
        long id = (long) 1;
        when(tweetItemService.getTweetById(id)).thenReturn(searchInMap(id, mockDatabase));
        assertNotNull(tweetItemRepository.getTweetById(id));
    }

    public TweetItemDto searchInMap(long id, Map<Long, TweetItemDto> map) {
        return map.get(id);
    }
    @Test
    public void shouldReturnAllTweetsWhenCalled(){
        when(tweetItemService.returnAllTweets()).thenReturn(mockDatabase.values().stream().toList());
        assertNotNull(tweetItemService.returnAllTweets());
    }


}