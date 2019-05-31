import asyncio

async def MyCoroutine():
    print("Hello, world")

def main():
    loop = asyncio.get_event_loop()
    loop.run_until_complete(MyCoroutine())
    loop.close()

if __name__ == '__main__':
    main()
