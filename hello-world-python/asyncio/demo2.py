import asyncio

async def MyCoroutine(future):
    await asyncio.sleep(1)
    future.set_result('myfuture 已执行')

async def main():
    future = asyncio.Future()
    await asyncio.ensure_future(MyCoroutine(future))
    print(future.result())

loop = asyncio.get_event_loop()
try:
    loop.run_until_complete(main())
finally:
    loop.close()
