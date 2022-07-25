struct rectangle
{
    unsigned int width;
    unsigned int height;
    char *name;
    void *obj;
};

int double_input(int inpout);
int calculate_area(struct rectangle *rect);