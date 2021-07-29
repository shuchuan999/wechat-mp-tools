package wechat.core.token;

import wechat.core.entity.AccessToken;

public class InMemoryRepository implements TokenRepository {
    @Override
    public AccessToken get() {
        return null;
    }

    @Override
    public void store(AccessToken accessToken) {

    }
}
