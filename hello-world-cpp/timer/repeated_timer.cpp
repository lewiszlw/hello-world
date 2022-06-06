//
// Created by Linwei Zhang on 2022/6/3.
//

#include <iostream>
#include <thread>
#include <chrono>
#include <future>

class RepeatedTimer {
public:
    RepeatedTimer();
    ~RepeatedTimer();
    void Schedule(int timeout_ms, std::function<void()> callback);
    void Reset(int timeout_ms);
private:
    std::thread thread_;
    std::function<void()> callback_;
    bool is_running_;
    int timeout_ms_;
    std::chrono::system_clock::time_point next_timeout_at_;
};

RepeatedTimer::RepeatedTimer() {
    is_running_ = false;
    timeout_ms_ = 0;
    next_timeout_at_ = std::chrono::system_clock::now();
}

RepeatedTimer::~RepeatedTimer() {
    is_running_ = false;
    next_timeout_at_ = std::chrono::system_clock::now() +  std::chrono::years(1000);
}

void RepeatedTimer::Schedule(int timeout_ms, std::function<void()> callback) {
    std::cout << "Schedule, timeout_ms " << timeout_ms << " " << std::chrono::system_clock::now().time_since_epoch().count() / 1000 << std::endl;
    timeout_ms_ = timeout_ms;
    next_timeout_at_ = std::chrono::system_clock::now() + std::chrono::milliseconds(timeout_ms);
    callback_ = callback;
    is_running_ = true;
    std::cout << "Schedule, before thread " << timeout_ms << " " << std::chrono::system_clock::now().time_since_epoch().count() / 1000 << std::endl;
    thread_ = std::thread([this]() {
        while (is_running_) {
            auto now = std::chrono::system_clock::now();
            if (now >= next_timeout_at_) {
                std::async(std::launch::async, callback_);
                next_timeout_at_ = now + std::chrono::milliseconds(timeout_ms_);
            }
            std::this_thread::sleep_for(std::chrono::milliseconds(5));
        }
    });
}
void RepeatedTimer::Reset(int timeout_ms) {
    timeout_ms_ = timeout_ms;
    next_timeout_at_ = std::chrono::system_clock::now() + std::chrono::milliseconds(timeout_ms);
}

int main() {
    RepeatedTimer timer;
    timer.Schedule(4000, []() {
        std::cout << "Hello, World! " << std::chrono::system_clock::now().time_since_epoch().count() / 1000 << std::endl;
    });

    std::this_thread::sleep_for(std::chrono::milliseconds(3000));

    std::cout << "Start reset " << std::chrono::system_clock::now().time_since_epoch().count() / 1000 << std::endl;
    timer.Reset(5000);
    std::cout << "End reset " << std::chrono::system_clock::now().time_since_epoch().count() / 1000 << std::endl;

    std::this_thread::sleep_for(std::chrono::milliseconds(4000));

    std::cout << "Start reset " << std::chrono::system_clock::now().time_since_epoch().count() / 1000 << std::endl;
    timer.Reset(3000);
    std::cout << "End reset " << std::chrono::system_clock::now().time_since_epoch().count() / 1000 << std::endl;

    std::this_thread::sleep_for(std::chrono::milliseconds(10000));
    return 0;
}