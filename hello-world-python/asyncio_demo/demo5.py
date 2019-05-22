import time
import asyncio

now = lambda : time.time()

async def MyCoroutine(x):
    print('Waiting: ', x)
    return 'Done after {}s'.format(x)

def callback(future):
    print('Callback: ', future.result())

start = now()

loop = asyncio.get_event_loop()
task = asyncio.ensure_future(MyCoroutine(3))
task.add_done_callback(callback)
loop.run_until_complete(task)

print('Task ret: ', task.result())
print('TIME: ', now() - start)
