#include <stdint.h>
#include <stdio.h>

struct Channel
{
    char *id;
    void *obj;
};

extern int32_t double_input(int32_t input);

extern create_channel_obj(struct Channel*);
extern print_channel_obj(struct Channel*);

extern create_channel_obj_rc(struct Channel*);
extern print_channel_obj_rc(struct Channel*);

extern create_channel_obj_box_rc(struct Channel*);
extern print_channel_obj_box_rc(struct Channel*);

int main() {
    int input = 4;
    int output = double_input(input);
    printf("%d * 2 = %d\n", input, output);

    // create channel
    struct Channel channel;
    channel.id = "channel_id";
    struct Channel *channel_ptr = &channel;
    printf("channel id: %s\n", channel_ptr->id);

    create_channel_obj(channel_ptr);

    print_channel_obj(channel_ptr);
    print_channel_obj(channel_ptr);

    // create channel2
    struct Channel channel2;
    channel2.id = "channel_id2";
    struct Channel *channel_ptr2 = &channel2;
    printf("channel id: %s\n", channel_ptr2->id);

    create_channel_obj_rc(channel_ptr2);

    print_channel_obj_rc(channel_ptr2);
    print_channel_obj_rc(channel_ptr2);

    // create channel3
    struct Channel channel3;
    channel3.id = "channel_id3";
    struct Channel *channel_ptr3 = &channel3;
    printf("channel id: %s\n", channel_ptr3->id);

    create_channel_obj_box_rc(channel_ptr3);

    print_channel_obj_box_rc(channel_ptr3);
    print_channel_obj_box_rc(channel_ptr3);
    return 0;
}