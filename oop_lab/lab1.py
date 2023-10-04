class Ball:
    def __init__(self, name, state="standing", speed=0, color="gray"):
        self.name = name
        self.state = state
        self.speed = speed
        self.color = color

    def inform_state(self):
        print(f"{self.name} is currently {self.state}.")
        if self.state == "moving":
            print(f"Speed: {self.speed}")
        print(f"Color: {self.color}")


if __name__ == "__main__":
    object1 = Ball("Ball 1", "standing", color="red")
    object1.inform_state()

    object2 = Ball("Ball 2", "moving", speed=10, color="blue")
    object2.inform_state()
    