import asyncio
import time

now = lambda: time.time()

async def MyCoroutine(x):
    print('Waiting: ', x)
    await asyncio.sleep(x)
    return 'Done after {}s'.format(x)

start = now()

tasks = [
            asyncio.ensure_future(MyCoroutine(1)),
            asyncio.ensure_future(MyCoroutine(2)),
            asyncio.ensure_future(MyCoroutine(4)),
        ]

loop = asyncio.get_event_loop()
loop.run_until_complete(asyncio.wait(tasks))

for task in tasks:
    print('Task ret: ', task.result())
print('TIME: ', now() - start)
