import asyncio
import random

async def MyCoroutine(id):
    process_time = random.randint(1, 5)
    await asyncio.sleep(process_time)
    print('协程：{}, 执行完毕。用时：{}秒'.format(id, process_time))

async def main():
    tasks = [asyncio.ensure_future(MyCoroutine(i)) for i in range(100)]
    await asyncio.gather(*tasks)

loop = asyncio.get_event_loop()
try:
    loop.run_until_complete(main())
finally:
    loop.close()
