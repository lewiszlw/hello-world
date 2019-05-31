import asyncio
import time

now = lambda : time.time()

async def MyCoroutine(x):
    print('Waiting', x)

start = now()

loop = asyncio.get_event_loop()
task = loop.create_task(MyCoroutine(2))
print(task)
loop.run_until_complete(task)
print(task)
print('TIME: ', now() - start)
