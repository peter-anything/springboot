if (redis.call('exists', KEYS[1]) == 1) then
    local stock = tonumber(redis.call('get', KEYS[1]));
    local buyNum = tonumber(ARGV[1]);
    if (stock - buyNum < 0) then
        return 0;
    else
        redis.call('incrby', KEYS[1], -buyNum);
        return 1;
    end;
end;
    return 0;